# KAFKA
KAFKA_CONTAINER := $$(docker-compose ps -q kafka)
KAFKA_HOST := localhost
KAFKA_PORT := 9092
KAFKA_COTS_TOPIC := cots-topic
KAFKA_SERVICE := cots-mock-kafka-1
KAFKA_WAIT_TIMEOUT_SECS := 60

.PHONY: start-kafka wait-for-kafka create-topic setup-kafka setup-postgres

start-kafka:
	@docker compose up -d kafka

wait-for-kafka:
	@timeout $(KAFKA_WAIT_TIMEOUT_SECS)s bash -c '\
		until docker exec $(KAFKA_SERVICE) kafka-topics.sh --bootstrap-server $(KAFKA_HOST):$(KAFKA_PORT) --list 2>&1 | grep -v WARN; do \
			sleep 1; \
		done' || (echo "Kafka failed to start" && exit 1)
	@echo "Kafka is ready!"

create-topic:
	@echo "Creating topic..."
	@docker exec $(KAFKA_CONTAINER) kafka-topics.sh --create --if-not-exists --topic $(KAFKA_COTS_TOPIC) --bootstrap-server $(KAFKA_HOST):$(KAFKA_PORT) --partitions 1 --replication-factor 1

list-topics:
	@docker exec -it $(KAFKA_SERVICE) kafka-topics.sh --bootstrap-server $(KAFKA_HOST):$(KAFKA_PORT) --list

setup-kafka: start-kafka wait-for-kafka create-topic

# POSTGRES
setup-postgres:
	@docker compose up -d postgres

# ALL
docker-up: setup-postgres setup-kafka

docker-down:
	docker compose down -v
