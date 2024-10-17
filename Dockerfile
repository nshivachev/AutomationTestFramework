# Use Maven with Java 22 as the base image
FROM maven:3.9.8-eclipse-temurin-22

# Install dependencies: curl, gnupg, wget, unzip, Xvfb, Google Chrome, and ChromeDriver
RUN apt-get update && apt-get install -y \
    curl gnupg wget unzip xvfb \
    ca-certificates && \
    curl -fsSL https://dl-ssl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Install the latest version of ChromeDriver (ensure it matches the installed Chrome)
RUN CHROME_DRIVER_VERSION=$(curl -sS https://chromedriver.storage.googleapis.com/LATEST_RELEASE) && \
    wget -q https://chromedriver.storage.googleapis.com/${CHROME_DRIVER_VERSION}/chromedriver_linux64.zip && \
    unzip chromedriver_linux64.zip -d /usr/local/bin/ && \
    rm chromedriver_linux64.zip && \
    chmod +x /usr/local/bin/chromedriver

# Set environment variables for headless Chrome execution
ENV DISPLAY=:99
ENV CHROME_DRIVER_PATH=/usr/local/bin/chromedriver

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the project files into the container
COPY . .

# Pre-download Maven dependencies to speed up builds
RUN mvn clean install -DskipTests

# Define the default command to run tests with Xvfb (for headless mode)
ENTRYPOINT ["xvfb-run", "-a", "mvn", "test"]
