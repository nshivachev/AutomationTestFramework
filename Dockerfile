# Use Maven with OpenJDK 22 as the base image
FROM maven:3.9.8-eclipse-temurin-22

# Set the working directory
WORKDIR /app

# Install necessary libraries
RUN apt-get update && \
    apt-get install -y libglib2.0-0 libnss3 libx11-xcb1 x11vnc xorg xvfb gtk2-engines-pixbuf dbus-x11 xfonts-base xfonts-100dpi xfonts-75dpi xfonts-cyrillic xfonts-scalable unzip && \
    apt-get clean && \
    rm -rf /tmp/* /var/tmp/*

# Copy the entire project into the container
COPY . /app

# Install Google Chrome (for Selenium)
RUN apt-get update && apt-get install -y \
    curl gnupg wget unzip xvfb ca-certificates && \
    curl -fsSL https://dl-ssl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Set ChromeDriver environment variable
RUN wget https://chromedriver.storage.googleapis.com/116.0.5845.96/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip -d /usr/local/bin && \
    rm chromedriver_linux64.zip

# Run tests using Maven
ENTRYPOINT ["mvn", "clean", "test"]
