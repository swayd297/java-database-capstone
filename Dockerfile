# Sử dụng image Java 17 chính thức
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ project vào trong container
COPY . .

# Biên dịch project bằng Maven Wrapper nếu có
RUN ./mvnw clean package -DskipTests

# Chạy ứng dụng Spring Boot
CMD ["java", "-jar", "app/target/back_end-0.0.1-SNAPSHOT.jar"]
