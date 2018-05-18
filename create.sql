
CREATE SEQUENCE app.contract_seq;

ALTER SEQUENCE app.contract_seq
    OWNER TO postgres;


CREATE SEQUENCE app.it_system_seq;

ALTER SEQUENCE app.it_system_seq
    OWNER TO postgres;


CREATE TABLE app.it_system
(
    id bigint NOT NULL DEFAULT nextval('app.it_system_seq'::regclass),
    name character varying(60) COLLATE pg_catalog."default",
    description character varying(120) COLLATE pg_catalog."default",
    techs text COLLATE pg_catalog."default",
    CONSTRAINT pk_it_system PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE app.it_system
    OWNER to postgres;


CREATE TABLE app.contract
(
    id bigint NOT NULL DEFAULT nextval('app.contract_seq'::regclass),
    it_system_id bigint,
    request character varying(8) COLLATE pg_catalog."default",
    order_number character varying(16) COLLATE pg_catalog."default",
    from_date date,
    to_date date,
    amount numeric(16,2),
    amount_type character varying(8) COLLATE pg_catalog."default",
    amount_period character varying(16) COLLATE pg_catalog."default",
    authorization_percent integer,
    active boolean,
    CONSTRAINT pk_contract PRIMARY KEY (id),
    CONSTRAINT fk_contract_it_system FOREIGN KEY (it_system_id)
        REFERENCES app.it_system (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE app.contract
    OWNER to postgres;