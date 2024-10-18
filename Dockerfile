FROM maven:3.9.8-eclipse-temurin-22

WORKDIR /app

COPY . /app

# Install Google Chrome (for Selenium)
RUN |
          sudo apt-get update
          sudo apt-get install -y chromium-browser
          
# INstall chromedriver
RUN |
          CHROME_DRIVER_VERSION=$(curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE)
          wget https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip
          unzip chromedriver_linux64.zip
          sudo mv chromedriver /usr/local/bin/
          sudo chown root:root /usr/local/bin/chromedriver
          sudo chmod +x /usr/local/bin/chromedriver

# Install dependencies
RUN mvn install -DskipTests

# Run tests using Maven
ENTRYPOINT ["mvn", "clean", "test"]
