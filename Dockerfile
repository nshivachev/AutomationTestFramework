# Base image with Java 11 (can change version as needed)
FROM openjdk:11-jdk-slim

# Install curl, wget, unzip, Chrome, and necessary dependencies
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    unzip \
    gnupg \
    --no-install-recommends

# Add Google Chrome's official GPG key
RUN curl -fsSL https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -

# Set up Chrome repository
RUN echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list

# Install Chrome browser and cleanup
RUN apt-get update && apt-get install -y google-chrome-stable \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install ChromeDriver (match with Chrome version)
RUN CHROME_DRIVER_VERSION=$(curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget -N https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip -d /usr/local/bin/ && \
    rm chromedriver_linux64.zip && \
    chmod +x /usr/local/bin/chromedriver

# Set ChromeDriver in PATH
ENV PATH="/usr/local/bin:${PATH}"

# Copy your project files (assuming your Java project uses Maven or Gradle)
WORKDIR /app
COPY . /app

# Install Maven (optional, if needed)
RUN apt-get update && apt-get install -y maven

# Run Maven to build the project (adjust as needed if using Gradle)
RUN mvn clean install

# Default command to run tests (can modify for specific test suites)
CMD ["mvn", "test"]
