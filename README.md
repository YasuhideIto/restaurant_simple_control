# 飲食店注文管理アプリ

## 概要

飲食店向けの注文管理アプリです。メニューの管理と注文のステータス管理ができます。  
Windows での開発からはじまり、低スペックノートPC（Linux Mint）への環境移行、Docker によるコンテナ化まで一人で取り組んだプロジェクトです。

## 公開URL

※ AWS EC2 デプロイ完了後に追記予定

## 使用技術

| カテゴリ | 技術 |
|---|---|
| 言語 | Java 21 |
| フレームワーク | Spring Boot |
| DB | MySQL 8.0 |
| ORM | JPA / Hibernate |
| テンプレートエンジン | Thymeleaf |
| フロントエンド | HTML / CSS |
| コンテナ | Docker / Docker Compose |
| バージョン管理 | Git / GitHub |
| 開発環境 | Linux Mint / VSCode / Eclipse |

## 機能一覧

### メニュー管理
- メニュー一覧表示
- メニュー追加・編集・削除
- メニュー重複チェック（バックエンド）

### 注文管理
- 注文一覧表示
- 注文追加
- 注文ステータス管理（未対応 → 調理中 → 完了）
- 注文ステータスの色分け表示
- 注文一括削除（確認アラート付き）

### 品質・セキュリティ
- バリデーション（フロント・バックエンド二重チェック）
- トランザクション処理
- 例外処理・エラーページ
- 排他制御

## ER図

※ 後日追加予定

## 画面キャプチャ

※ 後日追加予定

## 工夫した点

- **ステータスの色分け：** 忙しい現場ではヒューマンエラーが起きやすく、文字だけでは識別しづらいため、調理中はピンク・完了は緑で色分けしました。
- **メニュー重複チェック：** 同じメニュー名が登録されるとDBの一意性が失われるため、バックエンドで重複チェックを実装しました。
- **注文一括削除：** 営業終了後に不要なデータを効率よく削除できるよう、全件削除機能を追加しました。重要度の低いデータのため一括削除の使用性が高いと判断しました。
- **バリデーション二重チェック：** フロントのみだとブラウザの操作で回避できてしまうため、バックエンドでも `@Valid` で二重チェックしました。

## 開発の経緯

このプロジェクトは「低スペックなノートPCでもフルスタック開発ができる」ことを証明したくて取り組みました。

| フェーズ | 内容 |
|---|---|
| Windows 開発 | Eclipse + Spring Boot でアプリ開発。MySQL ローカル接続、GitHub への push/pull を習得 |
| 外部デプロイ | Render（Node.js + SQLite）、Railway（Java + MySQL）でデプロイを経験 |
| Linux 移行 | 低スペックノートPCに Lubuntu を試したが設定が難しく Linux Mint に切り替え |
| 環境構築 | Linux Mint に VSCode・JDK・Git・Docker Engine をインストール。Docker Desktop は重いため Engine のみ採用 |
| クロス環境対応 | `application.properties` を修正し、Windows と Linux Mint の両方で動作するよう調整 |
| Docker 化 | Dockerfile・docker-compose.yml を作成。`docker compose up --build` でローカルのコンテナ化に成功 |
| AWS デプロイ | EC2（Ubuntu 22.04 / t2.micro）へのデプロイを進行中 |

## 今後の課題

- [ ] AWS EC2 デプロイ完了・公開URL追記
- [ ] テストコード追加（JUnit）
- [ ] ログイン認証機能（Spring Security）
- [ ] テーブルごとの注文管理
- [ ] 検索・絞り込み機能
- [ ] ページネーション
- [ ] ER図・画面キャプチャ追加

## 開発環境

| 環境 | 内容 |
|---|---|
| メイン OS | Linux Mint（低スペックノートPC） |
| サブ OS | Windows（デスクトップPC） |
| IDE | VSCode / Eclipse |
| ローカル DB | MariaDB（Linux Mint）/ MySQL 8.0（Windows） |
| コンテナ | Docker Engine（Linux Mint）/ Docker Desktop（Windows） |
| 本番環境 | AWS EC2（デプロイ予定） |
