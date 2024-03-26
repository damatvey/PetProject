CREATE TABLE public.usr(
                           id bigint,
                           usrname varchar(100) NOT NULL,
                           email varchar(150) NOT NULL,
                           password varchar(150) NOT NULL,
                           created_at date NOT NULL DEFAULT CURRENT_DATE,
                           avatar_url varchar(250),
                           hearts bigint NOT NULL DEFAULT 3,
                           book_in_progress_id bigint,
                           favourite_genre_id bigint,
                           CONSTRAINT usr_pk PRIMARY KEY (id)

);

CREATE TABLE public.review(
                              id bigint,
                              likes_amount integer DEFAULT 0,
                              id_usr bigint,
                              id_book bigint,
                              updated_at timestamp DEFAULT now(),
                              review_text text NOT NULL,
                              created_at timestamp NOT NULL DEFAULT now(),
                              CONSTRAINT review_pk PRIMARY KEY (id)

);

CREATE TABLE public.genre(
                             id bigint,
                             name varchar(50),
                             CONSTRAINT genre_pk PRIMARY KEY (id),
                             CONSTRAINT genre_name_unique UNIQUE (name)

);

CREATE TABLE public.usr_role(
                                id bigint,
                                name varchar(50),
                                CONSTRAINT usr_role_pk PRIMARY KEY (id)

);

CREATE TABLE public.many_usr_role_has_many_usr(
                                                  id_usr_role bigint,
                                                  id_usr bigint,
                                                  CONSTRAINT many_usr_role_has_many_usr_pk PRIMARY KEY (id_usr_role,id_usr)

);

ALTER TABLE public.many_usr_role_has_many_usr ADD CONSTRAINT usr_role_fk FOREIGN KEY (id_usr_role)
    REFERENCES public.usr_role (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.many_usr_role_has_many_usr ADD CONSTRAINT usr_fk FOREIGN KEY (id_usr)
    REFERENCES public.usr (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

CREATE TABLE public.comment(
                               id bigint,
                               created_at timestamp NOT NULL DEFAULT now(),
                               id_usr bigint,
                               comment_text text NOT NULL,
                               updated_at timestamp DEFAULT now(),
                               likes_amount integer NOT NULL DEFAULT 0,
                               id_review bigint,
                               CONSTRAINT comment_pk PRIMARY KEY (id)

);

ALTER TABLE public.review ADD CONSTRAINT usr_fk FOREIGN KEY (id_usr)
    REFERENCES public.usr (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.comment ADD CONSTRAINT usr_fk FOREIGN KEY (id_usr)
    REFERENCES public.usr (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

CREATE TABLE public.book(
                            id bigint,
                            name varchar(250),
                            id_author bigint,
                            date_of_writing date NOT NULL,
                            thumbnail_url varchar(250),
                            CONSTRAINT book_pk PRIMARY KEY (id)

);

CREATE TABLE public.author(
                              id bigint,
                              name varchar(200),
                              date_of_birth date NOT NULL,
                              date_of_death date,
                              CONSTRAINT author_pk PRIMARY KEY (id)

);

CREATE TABLE public.many_book_has_many_genre(
                                                id_book bigint,
                                                id_genre bigint,
                                                CONSTRAINT many_book_has_many_genre_pk PRIMARY KEY (id_book,id_genre)

);

ALTER TABLE public.many_book_has_many_genre ADD CONSTRAINT book_fk FOREIGN KEY (id_book)
    REFERENCES public.book (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.many_book_has_many_genre ADD CONSTRAINT genre_fk FOREIGN KEY (id_genre)
    REFERENCES public.genre (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.book ADD CONSTRAINT author_fk FOREIGN KEY (id_author)
    REFERENCES public.author (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.review ADD CONSTRAINT book_fk FOREIGN KEY (id_book)
    REFERENCES public.book (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.comment ADD CONSTRAINT review_fk FOREIGN KEY (id_review)
    REFERENCES public.review (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.usr ADD CONSTRAINT book_fk FOREIGN KEY (book_in_progress_id)
    REFERENCES public.book (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE public.usr ADD CONSTRAINT genre_fk FOREIGN KEY (favourite_genre_id)
    REFERENCES public.genre (id) MATCH FULL
    ON DELETE SET NULL ON UPDATE CASCADE;

CREATE SEQUENCE author_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE book_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE comment_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE genre_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE review_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE usr_role_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE usr_seq START WITH 1 INCREMENT BY 50;
