-- public.test definition

-- Drop table

-- DROP TABLE public.test;

CREATE TABLE public.test (
	"content" text NULL,
	id serial4 NOT NULL
);
CREATE INDEX test_id_idx ON public.test (id int4_ops);