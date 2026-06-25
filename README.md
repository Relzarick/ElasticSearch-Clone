# ElasticSearch Clone

Simple clone.

---

## Setup

Place your dataset into the backend/data/ folder.

The system is designed to ingest only a single .csv file
(tested up to 1.6M records).
It does not support multiple datasets.

- The .csv file can be deleted after the first boot.

### Dataset used

https://www.kaggle.com/datasets/alanvourch/tmdb-movies-daily-updates?resource=download

---

## Running the App

Open the terminal and run the following commands.

On first start-up run

```bash
    docker compose up -d --build
```

Subsequent start-ups run

```bash
    docker compose up -d
```