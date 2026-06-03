# 1. Java 21 の軽量版を土台にする
FROM eclipse-temurin:21-jdk-alpine

# 2. コンテナ内の作業フォルダを設定
WORKDIR /app

# 3. プログラムをコンテナにコピー
COPY . .


# （※もしMavenやGradleを使う場合は、ここにビルドコマンドを書きます）
# 例: RUN ./mvnw package
# ↓権限を付与しておく（Mavenを使う場合）
RUN chmod +x ./mvnw

# 4. 起動コマンド（例: Mavenを使う場合）
CMD ["./mvnw", "spring-boot:run"]
