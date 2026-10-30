# Build
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./gradlew :server:installDist --no-daemon --console=plain

# Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/server/build/install/server /app
ENV PORT=8080
EXPOSE 8080
CMD ["/app/bin/server"]
