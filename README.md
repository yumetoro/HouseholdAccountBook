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

### 2. PostgreSQLのインストール

#### Mac (Homebrew)
```bash
brew install postgresql@15
brew services start postgresql@15
```

#### Windows
1. [PostgreSQL公式サイト](https://www.postgresql.org/download/windows/)からインストーラーをダウンロード
2. インストール時にパスワードを `postgres` に設定
3. デフォルトポート `5432` のまま進める

#### Ubuntu/Debian
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
```

### 3. データベース作成

```bash
# PostgreSQLに接続
psql -U postgres

# データベース作成
CREATE DATABASE household;

# 確認
\l

# 終了
\q
```

### 4. 設定ファイル

`BackEnd/src/main/resources/application.properties` を環境に合わせて編集:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/household
spring.datasource.username=postgres
spring.datasource.password=postgres
```

> **Note**: テーブルは `ddl-auto=update` により自動作成されます。手動でのテーブル作成は不要です。

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