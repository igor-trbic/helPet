-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.2
-- PostgreSQL version: 12.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: helpet | type: DATABASE --
-- -- DROP DATABASE IF EXISTS helpet;
-- CREATE DATABASE helpet;
-- -- ddl-end --
-- 

-- object: public.users_sequence | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.users_sequence CASCADE;
CREATE SEQUENCE public.users_sequence
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.users_sequence OWNER TO postgres;
-- ddl-end --

-- object: public."user" | type: TABLE --
-- DROP TABLE IF EXISTS public."user" CASCADE;
CREATE TABLE public."user" (
	id bigint NOT NULL DEFAULT nextval('public.users_sequence'::regclass),
	username text NOT NULL,
	password text NOT NULL,
	first_name text NOT NULL,
	last_name text NOT NULL,
	date_of_birth date NOT NULL,
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT udx_username UNIQUE (username)

);
-- ddl-end --
-- ALTER TABLE public."user" OWNER TO postgres;
-- ddl-end --

-- object: public.phone_sequence | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.phone_sequence CASCADE;
CREATE SEQUENCE public.phone_sequence
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.phone_sequence OWNER TO postgres;
-- ddl-end --

-- object: public.phone | type: TABLE --
-- DROP TABLE IF EXISTS public.phone CASCADE;
CREATE TABLE public.phone (
	id smallint NOT NULL DEFAULT nextval('public.phone_sequence'::regclass),
	phone_number text NOT NULL,
	phone_type text NOT NULL,
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	CONSTRAINT phone_pk PRIMARY KEY (id),
	CONSTRAINT udx_phone UNIQUE (phone_number)

);
-- ddl-end --
-- ALTER TABLE public.phone OWNER TO postgres;
-- ddl-end --

-- object: public.user_phone_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.user_phone_seq CASCADE;
CREATE SEQUENCE public.user_phone_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.user_phone_seq OWNER TO postgres;
-- ddl-end --

-- object: public.user_address_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.user_address_seq CASCADE;
CREATE SEQUENCE public.user_address_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.user_address_seq OWNER TO postgres;
-- ddl-end --

-- object: public.address_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.address_seq CASCADE;
CREATE SEQUENCE public.address_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.address_seq OWNER TO postgres;
-- ddl-end --

-- object: public.address | type: TABLE --
-- DROP TABLE IF EXISTS public.address CASCADE;
CREATE TABLE public.address (
	id bigint NOT NULL DEFAULT nextval('public.address_seq'::regclass),
	street_name text NOT NULL,
	house_number text,
	postal_code text,
	address_type text,
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	CONSTRAINT address_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.address OWNER TO postgres;
-- ddl-end --

-- object: public.email_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.email_seq CASCADE;
CREATE SEQUENCE public.email_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.email_seq OWNER TO postgres;
-- ddl-end --

-- object: public.email | type: TABLE --
-- DROP TABLE IF EXISTS public.email CASCADE;
CREATE TABLE public.email (
	id bigint NOT NULL DEFAULT nextval('public.email_seq'::regclass),
	email_address text NOT NULL,
	is_primary boolean NOT NULL,
	email_type text NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	status smallint NOT NULL,
	user_id bigint,
	CONSTRAINT email_pk PRIMARY KEY (id),
	CONSTRAINT udx_email_address UNIQUE (email_address)

);
-- ddl-end --
-- ALTER TABLE public.email OWNER TO postgres;
-- ddl-end --

-- object: public.business_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.business_seq CASCADE;
CREATE SEQUENCE public.business_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.business_seq OWNER TO postgres;
-- ddl-end --

-- object: public.business | type: TABLE --
-- DROP TABLE IF EXISTS public.business CASCADE;
CREATE TABLE public.business (
	id bigint NOT NULL DEFAULT nextval('public.business_seq'::regclass),
	business_name text NOT NULL,
	business_owner_id bigint NOT NULL,
	status smallint NOT NULL,
	tax_id text NOT NULL,
	national_id text NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	CONSTRAINT business_pk PRIMARY KEY (id),
	CONSTRAINT udx_business_name UNIQUE (business_name)

);
-- ddl-end --
COMMENT ON COLUMN public.business.tax_id IS E'PIB';
-- ddl-end --
COMMENT ON COLUMN public.business.national_id IS E'MB';
-- ddl-end --
-- ALTER TABLE public.business OWNER TO postgres;
-- ddl-end --

-- object: public.business_role_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.business_role_seq CASCADE;
CREATE SEQUENCE public.business_role_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.business_role_seq OWNER TO postgres;
-- ddl-end --

-- object: public.business_role | type: TABLE --
-- DROP TABLE IF EXISTS public.business_role CASCADE;
CREATE TABLE public.business_role (
	id bigint NOT NULL DEFAULT nextval('public.business_role_seq'::regclass),
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	business_staff_id bigint,
	business_role_type_id bigint,
	CONSTRAINT business_role_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.business_role OWNER TO postgres;
-- ddl-end --

-- object: public.business_role_type_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.business_role_type_seq CASCADE;
CREATE SEQUENCE public.business_role_type_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.business_role_type_seq OWNER TO postgres;
-- ddl-end --

-- object: public.business_role_type | type: TABLE --
-- DROP TABLE IF EXISTS public.business_role_type CASCADE;
CREATE TABLE public.business_role_type (
	id bigint NOT NULL DEFAULT nextval('public.business_role_type_seq'::regclass),
	business_role_name text NOT NULL,
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	business_id bigint,
	CONSTRAINT business_role_type_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.business_role_type OWNER TO postgres;
-- ddl-end --

-- object: public.business_staff_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.business_staff_seq CASCADE;
CREATE SEQUENCE public.business_staff_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.business_staff_seq OWNER TO postgres;
-- ddl-end --

-- object: public.business_staff | type: TABLE --
-- DROP TABLE IF EXISTS public.business_staff CASCADE;
CREATE TABLE public.business_staff (
	id bigint NOT NULL DEFAULT nextval('public.business_staff_seq'::regclass),
	user_id bigint,
	business_id bigint,
	CONSTRAINT business_member_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.business_staff OWNER TO postgres;
-- ddl-end --

-- object: public.user_address | type: TABLE --
-- DROP TABLE IF EXISTS public.user_address CASCADE;
CREATE TABLE public.user_address (
	id bigint NOT NULL DEFAULT nextval('public.user_address_seq'::regclass),
	address_id bigint,
	user_id bigint,
	CONSTRAINT users_address_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.user_address OWNER TO postgres;
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE public.email DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE public.email ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
REFERENCES public."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.user_phone | type: TABLE --
-- DROP TABLE IF EXISTS public.user_phone CASCADE;
CREATE TABLE public.user_phone (
	id bigint NOT NULL DEFAULT nextval('public.user_phone_seq'::regclass),
	user_id bigint,
	phone_id smallint,
	CONSTRAINT users_phone_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.user_phone OWNER TO postgres;
-- ddl-end --

-- object: phone_fk | type: CONSTRAINT --
-- ALTER TABLE public.user_phone DROP CONSTRAINT IF EXISTS phone_fk CASCADE;
ALTER TABLE public.user_phone ADD CONSTRAINT phone_fk FOREIGN KEY (phone_id)
REFERENCES public.phone (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_phone_uq | type: CONSTRAINT --
-- ALTER TABLE public.user_phone DROP CONSTRAINT IF EXISTS user_phone_uq CASCADE;
ALTER TABLE public.user_phone ADD CONSTRAINT user_phone_uq UNIQUE (phone_id);
-- ddl-end --

-- object: business_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_role_type DROP CONSTRAINT IF EXISTS business_fk CASCADE;
ALTER TABLE public.business_role_type ADD CONSTRAINT business_fk FOREIGN KEY (business_id)
REFERENCES public.business (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE public.user_address DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE public.user_address ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
REFERENCES public."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_staff DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE public.business_staff ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
REFERENCES public."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: business_staff_uq | type: CONSTRAINT --
-- ALTER TABLE public.business_staff DROP CONSTRAINT IF EXISTS business_staff_uq CASCADE;
ALTER TABLE public.business_staff ADD CONSTRAINT business_staff_uq UNIQUE (user_id);
-- ddl-end --

-- object: business_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_staff DROP CONSTRAINT IF EXISTS business_fk CASCADE;
ALTER TABLE public.business_staff ADD CONSTRAINT business_fk FOREIGN KEY (business_id)
REFERENCES public.business (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: business_staff_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_role DROP CONSTRAINT IF EXISTS business_staff_fk CASCADE;
ALTER TABLE public.business_role ADD CONSTRAINT business_staff_fk FOREIGN KEY (business_staff_id)
REFERENCES public.business_staff (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: business_role_uq | type: CONSTRAINT --
-- ALTER TABLE public.business_role DROP CONSTRAINT IF EXISTS business_role_uq CASCADE;
ALTER TABLE public.business_role ADD CONSTRAINT business_role_uq UNIQUE (business_staff_id);
-- ddl-end --

-- object: business_role_type_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_role DROP CONSTRAINT IF EXISTS business_role_type_fk CASCADE;
ALTER TABLE public.business_role ADD CONSTRAINT business_role_type_fk FOREIGN KEY (business_role_type_id)
REFERENCES public.business_role_type (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: business_role_uq1 | type: CONSTRAINT --
-- ALTER TABLE public.business_role DROP CONSTRAINT IF EXISTS business_role_uq1 CASCADE;
ALTER TABLE public.business_role ADD CONSTRAINT business_role_uq1 UNIQUE (business_role_type_id);
-- ddl-end --

-- object: public.pet_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.pet_seq CASCADE;
CREATE SEQUENCE public.pet_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.pet_seq OWNER TO postgres;
-- ddl-end --

-- object: public.pet | type: TABLE --
-- DROP TABLE IF EXISTS public.pet CASCADE;
CREATE TABLE public.pet (
	id bigint NOT NULL DEFAULT nextval('public.pet_seq'::regclass),
	name text NOT NULL,
	date_of_birth timestamp NOT NULL,
	note text,
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	CONSTRAINT pet_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.pet OWNER TO postgres;
-- ddl-end --

-- object: public.pet_owner_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.pet_owner_seq CASCADE;
CREATE SEQUENCE public.pet_owner_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.pet_owner_seq OWNER TO postgres;
-- ddl-end --

-- object: public.pet_owner | type: TABLE --
-- DROP TABLE IF EXISTS public.pet_owner CASCADE;
CREATE TABLE public.pet_owner (
	id bigint NOT NULL DEFAULT nextval('public.pet_owner_seq'::regclass),
	user_id bigint,
	pet_id bigint,
	CONSTRAINT pet_owner_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.pet_owner OWNER TO postgres;
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE public.pet_owner DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE public.pet_owner ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
REFERENCES public."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: pet_fk | type: CONSTRAINT --
-- ALTER TABLE public.pet_owner DROP CONSTRAINT IF EXISTS pet_fk CASCADE;
ALTER TABLE public.pet_owner ADD CONSTRAINT pet_fk FOREIGN KEY (pet_id)
REFERENCES public.pet (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: pet_owner_uq | type: CONSTRAINT --
-- ALTER TABLE public.pet_owner DROP CONSTRAINT IF EXISTS pet_owner_uq CASCADE;
ALTER TABLE public.pet_owner ADD CONSTRAINT pet_owner_uq UNIQUE (pet_id);
-- ddl-end --

-- object: public.pet_attribute_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.pet_attribute_seq CASCADE;
CREATE SEQUENCE public.pet_attribute_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.pet_attribute_seq OWNER TO postgres;
-- ddl-end --

-- object: public.pet_attribute | type: TABLE --
-- DROP TABLE IF EXISTS public.pet_attribute CASCADE;
CREATE TABLE public.pet_attribute (
	id bigint NOT NULL DEFAULT nextval('public.pet_attribute_seq'::regclass),
	name text NOT NULL,
	value text,
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	pet_id bigint,
	CONSTRAINT pet_attribute_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.pet_attribute OWNER TO postgres;
-- ddl-end --

-- object: pet_fk | type: CONSTRAINT --
-- ALTER TABLE public.pet_attribute DROP CONSTRAINT IF EXISTS pet_fk CASCADE;
ALTER TABLE public.pet_attribute ADD CONSTRAINT pet_fk FOREIGN KEY (pet_id)
REFERENCES public.pet (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.appointment_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.appointment_seq CASCADE;
CREATE SEQUENCE public.appointment_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.appointment_seq OWNER TO postgres;
-- ddl-end --

-- object: public.appointment | type: TABLE --
-- DROP TABLE IF EXISTS public.appointment CASCADE;
CREATE TABLE public.appointment (
	id bigint NOT NULL DEFAULT nextval('public.appointment_seq'::regclass),
	date timestamp NOT NULL,
	note text,
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	business_id bigint,
	user_id bigint,
	pet_id bigint,
	CONSTRAINT appointment_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.appointment OWNER TO postgres;
-- ddl-end --

-- object: business_fk | type: CONSTRAINT --
-- ALTER TABLE public.appointment DROP CONSTRAINT IF EXISTS business_fk CASCADE;
ALTER TABLE public.appointment ADD CONSTRAINT business_fk FOREIGN KEY (business_id)
REFERENCES public.business (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE public.appointment DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE public.appointment ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
REFERENCES public."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: pet_fk | type: CONSTRAINT --
-- ALTER TABLE public.appointment DROP CONSTRAINT IF EXISTS pet_fk CASCADE;
ALTER TABLE public.appointment ADD CONSTRAINT pet_fk FOREIGN KEY (pet_id)
REFERENCES public.pet (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: appointment_uq | type: CONSTRAINT --
-- ALTER TABLE public.appointment DROP CONSTRAINT IF EXISTS appointment_uq CASCADE;
ALTER TABLE public.appointment ADD CONSTRAINT appointment_uq UNIQUE (pet_id);
-- ddl-end --

-- object: public.therapy_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.therapy_seq CASCADE;
CREATE SEQUENCE public.therapy_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.therapy_seq OWNER TO postgres;
-- ddl-end --

-- object: public.therapy | type: TABLE --
-- DROP TABLE IF EXISTS public.therapy CASCADE;
CREATE TABLE public.therapy (
	id bigint NOT NULL DEFAULT nextval('public.therapy_seq'::regclass),
	status smallint NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	CONSTRAINT therapy_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.therapy OWNER TO postgres;
-- ddl-end --

-- object: public.examination_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.examination_seq CASCADE;
CREATE SEQUENCE public.examination_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.examination_seq OWNER TO postgres;
-- ddl-end --

-- object: public.examination | type: TABLE --
-- DROP TABLE IF EXISTS public.examination CASCADE;
CREATE TABLE public.examination (
	id bigint NOT NULL DEFAULT nextval('public.examination_seq'::regclass),
	note text NOT NULL,
	appointment_id bigint,
	diagnose_id bigint,
	therapy_id bigint,
	CONSTRAINT examination_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.examination OWNER TO postgres;
-- ddl-end --

-- object: public.diagnose_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.diagnose_seq CASCADE;
CREATE SEQUENCE public.diagnose_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.diagnose_seq OWNER TO postgres;
-- ddl-end --

-- object: public.diagnose | type: TABLE --
-- DROP TABLE IF EXISTS public.diagnose CASCADE;
CREATE TABLE public.diagnose (
	id bigint NOT NULL DEFAULT nextval('public.diagnose_seq'::regclass),
	name text NOT NULL,
	description text NOT NULL,
	CONSTRAINT diagnose_pk PRIMARY KEY (id),
	CONSTRAINT udx_diagnose_name UNIQUE (name)

);
-- ddl-end --
-- ALTER TABLE public.diagnose OWNER TO postgres;
-- ddl-end --

-- object: public.medication_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.medication_seq CASCADE;
CREATE SEQUENCE public.medication_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.medication_seq OWNER TO postgres;
-- ddl-end --

-- object: public.medication | type: TABLE --
-- DROP TABLE IF EXISTS public.medication CASCADE;
CREATE TABLE public.medication (
	id bigint NOT NULL DEFAULT nextval('public.medication_seq'::regclass),
	name text NOT NULL,
	description text NOT NULL,
	dose text,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	status smallint NOT NULL,
	CONSTRAINT medication_pk PRIMARY KEY (id),
	CONSTRAINT udx_medication_name UNIQUE (name)

);
-- ddl-end --
-- ALTER TABLE public.medication OWNER TO postgres;
-- ddl-end --

-- object: public.therapy_frequency_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.therapy_frequency_seq CASCADE;
CREATE SEQUENCE public.therapy_frequency_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.therapy_frequency_seq OWNER TO postgres;
-- ddl-end --

-- object: public.therapy_frequency | type: TABLE --
-- DROP TABLE IF EXISTS public.therapy_frequency CASCADE;
CREATE TABLE public.therapy_frequency (
	id bigint NOT NULL DEFAULT nextval('public.therapy_frequency_seq'::regclass),
	from_date timestamp NOT NULL,
	thru_date timestamp NOT NULL,
	description text NOT NULL,
	times smallint NOT NULL,
	times_per text NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	status smallint NOT NULL,
	therapy_id bigint,
	CONSTRAINT therapy_frequency_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.therapy_frequency OWNER TO postgres;
-- ddl-end --

-- object: public.therapy_medication_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.therapy_medication_seq CASCADE;
CREATE SEQUENCE public.therapy_medication_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.therapy_medication_seq OWNER TO postgres;
-- ddl-end --

-- object: public.therapy_medication | type: TABLE --
-- DROP TABLE IF EXISTS public.therapy_medication CASCADE;
CREATE TABLE public.therapy_medication (
	id bigint NOT NULL DEFAULT nextval('public.therapy_medication_seq'::regclass),
	description text,
	from_date timestamp NOT NULL,
	thru_date timestamp NOT NULL,
	times smallint NOT NULL,
	times_per text NOT NULL,
	created_on timestamp NOT NULL,
	created_by text NOT NULL,
	updated_on timestamp,
	updated_by text,
	status smallint NOT NULL,
	medication_id bigint,
	therapy_id bigint,
	CONSTRAINT therapy_medication_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.therapy_medication OWNER TO postgres;
-- ddl-end --

-- object: appointment_fk | type: CONSTRAINT --
-- ALTER TABLE public.examination DROP CONSTRAINT IF EXISTS appointment_fk CASCADE;
ALTER TABLE public.examination ADD CONSTRAINT appointment_fk FOREIGN KEY (appointment_id)
REFERENCES public.appointment (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: examination_uq | type: CONSTRAINT --
-- ALTER TABLE public.examination DROP CONSTRAINT IF EXISTS examination_uq CASCADE;
ALTER TABLE public.examination ADD CONSTRAINT examination_uq UNIQUE (appointment_id);
-- ddl-end --

-- object: diagnose_fk | type: CONSTRAINT --
-- ALTER TABLE public.examination DROP CONSTRAINT IF EXISTS diagnose_fk CASCADE;
ALTER TABLE public.examination ADD CONSTRAINT diagnose_fk FOREIGN KEY (diagnose_id)
REFERENCES public.diagnose (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: examination_uq1 | type: CONSTRAINT --
-- ALTER TABLE public.examination DROP CONSTRAINT IF EXISTS examination_uq1 CASCADE;
ALTER TABLE public.examination ADD CONSTRAINT examination_uq1 UNIQUE (diagnose_id);
-- ddl-end --

-- object: therapy_fk | type: CONSTRAINT --
-- ALTER TABLE public.examination DROP CONSTRAINT IF EXISTS therapy_fk CASCADE;
ALTER TABLE public.examination ADD CONSTRAINT therapy_fk FOREIGN KEY (therapy_id)
REFERENCES public.therapy (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: examination_uq2 | type: CONSTRAINT --
-- ALTER TABLE public.examination DROP CONSTRAINT IF EXISTS examination_uq2 CASCADE;
ALTER TABLE public.examination ADD CONSTRAINT examination_uq2 UNIQUE (therapy_id);
-- ddl-end --

-- object: therapy_fk | type: CONSTRAINT --
-- ALTER TABLE public.therapy_frequency DROP CONSTRAINT IF EXISTS therapy_fk CASCADE;
ALTER TABLE public.therapy_frequency ADD CONSTRAINT therapy_fk FOREIGN KEY (therapy_id)
REFERENCES public.therapy (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: medication_fk | type: CONSTRAINT --
-- ALTER TABLE public.therapy_medication DROP CONSTRAINT IF EXISTS medication_fk CASCADE;
ALTER TABLE public.therapy_medication ADD CONSTRAINT medication_fk FOREIGN KEY (medication_id)
REFERENCES public.medication (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: therapy_fk | type: CONSTRAINT --
-- ALTER TABLE public.therapy_medication DROP CONSTRAINT IF EXISTS therapy_fk CASCADE;
ALTER TABLE public.therapy_medication ADD CONSTRAINT therapy_fk FOREIGN KEY (therapy_id)
REFERENCES public.therapy (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.business_address_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.business_address_seq CASCADE;
CREATE SEQUENCE public.business_address_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.business_address_seq OWNER TO postgres;
-- ddl-end --

-- object: public.business_address | type: TABLE --
-- DROP TABLE IF EXISTS public.business_address CASCADE;
CREATE TABLE public.business_address (
	id bigint NOT NULL DEFAULT nextval('public.business_address_seq'::regclass),
	business_id bigint,
	address_id bigint,
	CONSTRAINT business_address_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.business_address OWNER TO postgres;
-- ddl-end --

-- object: business_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_address DROP CONSTRAINT IF EXISTS business_fk CASCADE;
ALTER TABLE public.business_address ADD CONSTRAINT business_fk FOREIGN KEY (business_id)
REFERENCES public.business (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: address_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_address DROP CONSTRAINT IF EXISTS address_fk CASCADE;
ALTER TABLE public.business_address ADD CONSTRAINT address_fk FOREIGN KEY (address_id)
REFERENCES public.address (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: business_address_uq | type: CONSTRAINT --
-- ALTER TABLE public.business_address DROP CONSTRAINT IF EXISTS business_address_uq CASCADE;
ALTER TABLE public.business_address ADD CONSTRAINT business_address_uq UNIQUE (address_id);
-- ddl-end --

-- object: public.business_phone_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.business_phone_seq CASCADE;
CREATE SEQUENCE public.business_phone_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 100
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.business_phone_seq OWNER TO postgres;
-- ddl-end --

-- object: public.business_phone | type: TABLE --
-- DROP TABLE IF EXISTS public.business_phone CASCADE;
CREATE TABLE public.business_phone (
	id bigint NOT NULL DEFAULT nextval('public.business_phone_seq'::regclass),
	business_id bigint,
	phone_id smallint,
	CONSTRAINT business_phone_pk PRIMARY KEY (id)

);
-- ddl-end --
-- ALTER TABLE public.business_phone OWNER TO postgres;
-- ddl-end --

-- object: business_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_phone DROP CONSTRAINT IF EXISTS business_fk CASCADE;
ALTER TABLE public.business_phone ADD CONSTRAINT business_fk FOREIGN KEY (business_id)
REFERENCES public.business (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: phone_fk | type: CONSTRAINT --
-- ALTER TABLE public.business_phone DROP CONSTRAINT IF EXISTS phone_fk CASCADE;
ALTER TABLE public.business_phone ADD CONSTRAINT phone_fk FOREIGN KEY (phone_id)
REFERENCES public.phone (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: business_phone_uq | type: CONSTRAINT --
-- ALTER TABLE public.business_phone DROP CONSTRAINT IF EXISTS business_phone_uq CASCADE;
ALTER TABLE public.business_phone ADD CONSTRAINT business_phone_uq UNIQUE (phone_id);
-- ddl-end --

-- object: address_fk | type: CONSTRAINT --
-- ALTER TABLE public.user_address DROP CONSTRAINT IF EXISTS address_fk CASCADE;
ALTER TABLE public.user_address ADD CONSTRAINT address_fk FOREIGN KEY (address_id)
REFERENCES public.address (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_address_uq | type: CONSTRAINT --
-- ALTER TABLE public.user_address DROP CONSTRAINT IF EXISTS user_address_uq CASCADE;
ALTER TABLE public.user_address ADD CONSTRAINT user_address_uq UNIQUE (address_id);
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE public.user_phone DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE public.user_phone ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
REFERENCES public."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.user_auth_token | type: TABLE --
-- DROP TABLE IF EXISTS public.user_auth_token CASCADE;
CREATE TABLE public.user_auth_token (
	token text NOT NULL,
	created_on timestamp NOT NULL,
	expiry_time timestamp NOT NULL,
	user_id bigint,
	CONSTRAINT user_auth_token_pk PRIMARY KEY (token)

);
-- ddl-end --
-- ALTER TABLE public.user_auth_token OWNER TO postgres;
-- ddl-end --

-- object: user_fk | type: CONSTRAINT --
-- ALTER TABLE public.user_auth_token DROP CONSTRAINT IF EXISTS user_fk CASCADE;
ALTER TABLE public.user_auth_token ADD CONSTRAINT user_fk FOREIGN KEY (user_id)
REFERENCES public."user" (id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: user_auth_token_uq | type: CONSTRAINT --
-- ALTER TABLE public.user_auth_token DROP CONSTRAINT IF EXISTS user_auth_token_uq CASCADE;
ALTER TABLE public.user_auth_token ADD CONSTRAINT user_auth_token_uq UNIQUE (user_id);
-- ddl-end --

-- object: public.auth_token_attribute | type: TABLE --
-- DROP TABLE IF EXISTS public.auth_token_attribute CASCADE;
CREATE TABLE public.auth_token_attribute (
	attr_name text NOT NULL,
	attr_value text NOT NULL,
	created_on timestamp NOT NULL,
	created_by text,
	token_user_auth_token text
);
-- ddl-end --
-- ALTER TABLE public.auth_token_attribute OWNER TO postgres;
-- ddl-end --

-- object: user_auth_token_fk | type: CONSTRAINT --
-- ALTER TABLE public.auth_token_attribute DROP CONSTRAINT IF EXISTS user_auth_token_fk CASCADE;
ALTER TABLE public.auth_token_attribute ADD CONSTRAINT user_auth_token_fk FOREIGN KEY (token_user_auth_token)
REFERENCES public.user_auth_token (token) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --


