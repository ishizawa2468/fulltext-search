.PHONY: up down logs psql build run clean

# コンテナ起動／停止
up:
	docker-compose up -d

down:
	docker-compose down

logs:
	docker-compose logs -f

# DB に psql で入る
psql:
	docker-compose exec db psql -U app -d devdb -p 5433

# Spring Boot ビルド＆起動（ローカル実行）
build:
	./gradlew clean build

run:
	SPRING_PROFILES_ACTIVE=local ./gradlew bootRun

clean:
	./gradlew clean
