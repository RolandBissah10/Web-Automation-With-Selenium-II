# Use Maven with JDK 17 as the build environment
FROM maven:3.9-eclipse-temurin-17

# Set working directory inside the container
WORKDIR /app

# Install Google Chrome for Selenium
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" \
       >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Copy Maven dependencies first for better layer caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the project
COPY src ./src
COPY resources ./resources

# Run tests when container starts
CMD ["mvn", "test", "-Dheadless=true"]