# Maven image based on JDK 16
FROM maven:3.8.5-eclipse-temurin-16

# Set working directory
WORKDIR /workspace
ENV DEBIAN_FRONTEND=noninteractive

# Install necessary libraries
RUN apt-get update && \
    apt-get install -y libglib2.0-0 libnss3 libx11-xcb1 x11vnc xorg xvfb gtk2-engines-pixbuf dbus-x11 xfonts-base xfonts-100dpi xfonts-75dpi xfonts-cyrillic xfonts-scalable unzip && \
    apt-get clean && \
    rm -rf /tmp/* /var/tmp/*

RUN mkdir /opt/drivers

# installing google-chrome-stable
RUN apt-get install -y gnupg wget curl unzip --no-install-recommends; \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | \
    gpg --no-default-keyring --keyring gnupg-ring:/etc/apt/trusted.gpg.d/google.gpg --import; \
    chmod 644 /etc/apt/trusted.gpg.d/google.gpg; \
    echo "deb https://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list; \
    apt-get update -y; \
    apt-get install -y google-chrome-stable;

# installing chromedriver
RUN CHROMEDRIVER_VERSION=$(curl https://googlechromelabs.github.io/chrome-for-testing/LATEST_RELEASE_STABLE); \
    wget -N https://storage.googleapis.com/chrome-for-testing-public/$CHROMEDRIVER_VERSION/linux64/chromedriver-linux64.zip -P ~/ && \
    unzip ~/chromedriver-linux64.zip -d ~/ && \
    rm ~/chromedriver-linux64.zip && \
    mv -f ~/chromedriver-linux64/chromedriver /opt/drivers/ && \
    rm -rf ~/chromedriver-linux64 \

# Create maven user
RUN useradd -u 1000 -m -d /var/maven maven
RUN chown -R maven:maven ./
ENV MAVEN_CONFIG /var/maven/.m2

COPY entrypoint.sh /entrypoint

ENTRYPOINT ["/entrypoint"]