# 飲食店注文管理アプリ

## 概要
飲食店の従業員向けの注文管理アプリです。
オーダーを取るページとオーダ状況を管理できるページです。
メニューの管理と注文のステータス管理ができます。  
Windows での開発からはじまり、低スペックノートPC（Linux Mint）への環境移行、Docker によるコンテナ化、AWS EC2 への本番デプロイ、CI/CD パイプラインの構築まで一人で取り組んだプロジェクトです。

## 公開URL
**https://restaurant-order-app.com**

## 使用技術
| カテゴリ | 技術 |
|---|---|
| 言語 | Java 21 |
| フレームワーク | Spring Boot |
| DB | MariaDB |
| ORM | JPA / Hibernate |
| テンプレートエンジン | Thymeleaf |
| フロントエンド | HTML / CSS |
| テスト | JUnit 5 / Mockito |
| CI/CD | GitHub Actions |
| コンテナ | Docker / Docker Compose |
| Webサーバー | Nginx（リバースプロキシ） |
| SSL | Let's Encrypt（Certbot） |
| クラウド | AWS EC2（t2.micro） |
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
- 単体テスト（Mockito によるController層のテスト）

## ER図
※ 後日追加予定

## 画面キャプチャ
※ 後日追加予定

## 工夫した点
- **ステータスの色分け：** 忙しい現場ではヒューマンエラーが起きやすく、文字だけでは識別しづらいため、調理中はピンク・完了は緑で色分けしました。
- **メニュー重複チェック：** 同じメニュー名が登録されるとDBの一意性が失われるため、バックエンドで重複チェックを実装しました。
- **注文一括削除：** 営業終了後に不要なデータを効率よく削除できるよう、全件削除機能を追加しました。重要度の低いデータのため一括削除の使用性が高いと判断しました。
- **バリデーション二重チェック：** フロントのみだとブラウザの操作で回避できてしまうため、バックエンドでも `@Valid` で二重チェックしました。
- **CI/CDによる自動化：** `main`ブランチへのマージをトリガーに、GitHub Actionsでテストの自動実行とEC2への自動デプロイを構築し、手動デプロイのミスを防ぐ仕組みを整えました。

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
| AWS デプロイ | EC2（t2.micro）へデプロイ。Nginx + Let's Encrypt で HTTPS 化し、独自ドメインで本番公開完了 |
| OS 移行 | 開発マシンを Debian 13 + XFCE に移行。同一手順での環境再現を確認 |
| テスト導入 | Mockito を用いた Controller 層の単体テストを実装。Mock を使った振る舞い検証の型を習得 |
| CI/CD 構築 | GitHub Actions で CI（テスト自動実行）と CD（EC2への自動デプロイ）を構築。Secrets を用いた鍵情報の安全な管理も実施 |

## CI/CD パイプライン
| 項目 | 内容 |
|---|---|
| CI（テスト自動実行） | `experiment`・`main` ブランチへの push をトリガーに、GitHub Actions 上で JUnit テストを自動実行 |
| CD（自動デプロイ） | `main` ブランチへの push をトリガーに、GitHub Actions が SSH 経由で EC2 に接続し、`git pull` → `docker compose up --build -d` を自動実行 |
| 認証情報管理 | SSH秘密鍵・接続先ホスト・ユーザー名は GitHub Secrets で暗号化管理 |

## インフラ構成
| 項目 | 内容 |
|---|---|
| クラウド | AWS EC2（t2.micro） |
| OS（サーバー） | Debian 13 |
| IP | Elastic IP（固定） |
| ドメイン | restaurant-order-app.com（お名前.com） |
| Webサーバー | Nginx（リバースプロキシ：ポート80/443 → 8080） |
| SSL証明書 | Let's Encrypt（Certbot で自動取得・設定） |
| コンテナ構成 | Docker Compose で Java（Spring Boot）と MariaDB の2コンテナを一括管理 |
| swap | 1GB 追加済み（t2.micro のメモリ不足対策） |

## 今後の課題
- [x] AWS EC2 デプロイ完了・公開URL追記
- [x] CI/CD パイプライン構築（GitHub Actions）
- [x] テストコード追加（JUnit / Mockito、Controller層から着手）
- [ ] テストの網羅範囲を拡大（バリデーション・結合テスト・全Controller）
- [ ] ログイン認証機能（Spring Security）
- [ ] テーブルごとの注文管理
- [ ] 検索・絞り込み機能
- [ ] ページネーション
- [ ] ER図・画面キャプチャ追加

## 開発環境
| 環境 | 内容 |
|---|---|
| メイン OS | Debian 13 + XFCE（Linux Mint から移行） |
| サブ OS | Windows（デスクトップPC） |
| IDE | VSCode / Eclipse |
| ローカル DB | MariaDB（Linux）/ MySQL 8.0（Windows） |
| コンテナ | Docker Engine（Linux）/ Docker Desktop（Windows） |
| 本番環境 | AWS EC2（https://restaurant-order-app.com） |
