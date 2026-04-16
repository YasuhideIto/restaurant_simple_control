# 飲食店注文管理アプリ

## 概要
飲食店向けの注文管理アプリです。メニューの管理と注文のステータス管理ができます。

## 公開URL
[restaurantsimplecontrol-production.up.railway.app](https://restaurantsimplecontrol-production.up.railway.app/)

## 使用技術
- Java 21
- Spring Boot 4.0.5
- MySQL 8.0
- Thymeleaf
- HTML / CSS
- JPA / Hibernate

## 機能
- メニュー一覧表示
- メニュー追加
- 注文一覧表示
- 注文追加
- 注文ステータス管理（未対応 → 調理中 → 完了）
- トランザクション処理

## 開発環境
- Eclipse（Spring Bootプラグイン）
- MySQL 8.0（ローカル）
- Railway（本番環境）

## 画面構成
- `/order` 注文一覧・注文追加
- `/menu` メニュー一覧・メニュー追加
