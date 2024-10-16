# Use OpenJDK 22 (or switch to other builds if needed)
FROM openjdk:22-jdk-slim

# Install curl, wget, unzip, Chrome, and other dependencies
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    unzip \
    gnupg \
    maven \
    --no-install-recommends

# Add Google Chrome repository and install Chrome
RUN curl -fsSL https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Install ChromeDriver matching Chrome version
RUN CHROME_DRIVER_VERSION=$(curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget -N https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip -d /usr/local/bin/ && \
    rm chromedriver_linux64.zip && \
    chmod +x /usr/local/bin/chromedriver

# Set ChromeDriver in PATH
ENV PATH="/usr/local/bin:${PATH}"

# Copy project files and set working directory
WORKDIR /app
COPY . /app

# Install Maven (optional, if needed)
RUN apt-get update && apt-get install -y maven

# Run Maven to build the project (adjust as needed if using Gradle)
RUN mvn clean install

# Default command to run tests (can modify for specific test suites)
CMD ["mvn", "test"]
