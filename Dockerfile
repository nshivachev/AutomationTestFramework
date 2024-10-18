# Use Maven with OpenJDK 22 as the base image
FROM maven:3.9.8-eclipse-temurin-22

# Set the working directory
WORKDIR /app

# Install dependencies (including wget and unzip)
RUN apt-get update && apt-get install -y \
    curl gnupg wget unzip xvfb ca-certificates && \
    curl -fsSL https://dl-ssl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Download the latest compatible ChromeDriver
RUN LATEST_VERSION=$(curl -s https://chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget https://chromedriver.storage.googleapis.com/${LATEST_VERSION}/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip -d /usr/local/bin && \
    rm chromedriver_linux64.zip

# Run tests using Maven
ENTRYPOINT ["mvn", "clean", "test"]
