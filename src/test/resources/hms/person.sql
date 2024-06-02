CREATE TABLE person
(
    id      INT,
    name    STRING,
    rptg_dt STRING
)
    using PARQUET
    PARTITIONED BY (rptg_dt)