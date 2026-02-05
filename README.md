# 家計簿アプリケーション (HouseholdAccountBook)

家計の収支を管理するためのWebアプリケーションです。

## 技術スタック

- **バックエンド**: Spring Boot 3.4.2
- **言語**: Java 21
- **データベース**: PostgreSQL
- **テンプレートエンジン**: Thymeleaf
- **ビルドツール**: Gradle

## 機能

### ダッシュボード
- 当月の収入・支出・残高を一覧表示

### 収入管理
- 収入の登録・一覧表示・削除
- カテゴリ: 給料、副業、その他収入

### 支出管理
- 支出の登録・一覧表示・削除
- カテゴリ: 食費、住居費、光熱費、交通費、通信費、娯楽費、その他支出

### カテゴリ管理
- カスタムカテゴリの追加・削除

## セットアップ

### 1. 前提条件

- Java 21 以上
- PostgreSQL
- Gradle (またはGradle Wrapper使用)

### 2. データベース設定

PostgreSQLで以下のデータベースを作成してください:

```sql
CREATE DATABASE household;
```

### 3. 設定ファイル

`BackEnd/src/main/resources/application.properties` を環境に合わせて編集:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/household
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 4. 起動方法

```bash
cd BackEnd
./gradlew bootRun
```

アプリケーションは http://localhost:8080 で起動します。

## プロジェクト構成

```
BackEnd/
├── src/main/java/com/example/demo/
│   ├── HouseholdApp.java          # メインクラス
│   ├── DataInitializer.java       # 初期データ投入
│   ├── controller/
│   │   └── HomeController.java    # コントローラー
│   ├── model/
│   │   ├── Category.java          # カテゴリエンティティ
│   │   └── Transaction.java       # 取引エンティティ
│   └── repository/
│       ├── CategoryRepository.java
│       └── TransactionRepository.java
└── src/main/resources/
    ├── application.properties     # 設定ファイル
    └── templates/                 # Thymeleafテンプレート
        ├── layout.html
        ├── dashboard.html
        ├── transactions.html
        └── category.html
```

## 画面一覧

| パス | 説明 |
|------|------|
| `/` | ダッシュボード |
| `/income` | 収入管理 |
| `/expense` | 支出管理 |
| `/category` | カテゴリ管理 |

## ビルド

```bash
cd BackEnd
./gradlew build
```

ビルド成果物は `BackEnd/build/libs/` に生成されます。

## テスト

```bash
cd BackEnd
./gradlew test
```