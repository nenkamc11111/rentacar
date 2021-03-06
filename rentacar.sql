PGDMP     $                    y            rentacar    13.1    13.1     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24631    rentacar    DATABASE     e   CREATE DATABASE rentacar WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'German_Germany.1252';
    DROP DATABASE rentacar;
                postgres    false            �            1259    24703    auto    TABLE     B  CREATE TABLE public.auto (
    idauto smallint NOT NULL,
    mark character(40),
    kmstand bigint,
    zustand character(30),
    filiale character(40),
    created_at date DEFAULT CURRENT_DATE,
    preis double precision,
    bild character(50),
    beschreibung character(100),
    frei smallint DEFAULT 1 NOT NULL
);
    DROP TABLE public.auto;
       public         heap    postgres    false            �            1259    24708    auto_id_seq    SEQUENCE     �   ALTER TABLE public.auto ALTER COLUMN idauto ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.auto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    203            �            1259    24641    historyauto    TABLE     �   CREATE TABLE public.historyauto (
    idhistauto smallint NOT NULL,
    idauto smallint NOT NULL,
    idkunde smallint NOT NULL,
    abholdatum date,
    ruckgabedatum date,
    created_at date DEFAULT CURRENT_DATE,
    idmieten smallint NOT NULL
);
    DROP TABLE public.historyauto;
       public         heap    postgres    false            �            1259    24713    kunde    TABLE     	  CREATE TABLE public.kunde (
    idkunde smallint NOT NULL,
    vorname character(40),
    name character(40),
    stadt character(40),
    alter smallint,
    idnummer character(40),
    fnummer character(40),
    email character(40),
    password character(50)
);
    DROP TABLE public.kunde;
       public         heap    postgres    false            �            1259    24747    kunde_idkunde_seq    SEQUENCE     �   ALTER TABLE public.kunde ALTER COLUMN idkunde ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.kunde_idkunde_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    205            �            1259    24651    mieten    TABLE     �   CREATE TABLE public.mieten (
    idmieten smallint NOT NULL,
    idauto smallint NOT NULL,
    idkunde smallint NOT NULL,
    abholdatum date,
    ruckgabedatum date,
    created_at date DEFAULT CURRENT_DATE
);
    DROP TABLE public.mieten;
       public         heap    postgres    false            �            1259    24749    payment    TABLE     �   CREATE TABLE public.payment (
    idpayment smallint NOT NULL,
    idauto smallint,
    idmieten smallint,
    idkunde smallint,
    preis double precision,
    ruckgabedatum date,
    abholdatum date
);
    DROP TABLE public.payment;
       public         heap    postgres    false            �            1259    24754    payment_idpayment_seq    SEQUENCE     �   ALTER TABLE public.payment ALTER COLUMN idpayment ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.payment_idpayment_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    207            �            1259    24655    rent_idmieten_seq    SEQUENCE     �   ALTER TABLE public.mieten ALTER COLUMN idmieten ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.rent_idmieten_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    201            �          0    24703    auto 
   TABLE DATA           t   COPY public.auto (idauto, mark, kmstand, zustand, filiale, created_at, preis, bild, beschreibung, frei) FROM stdin;
    public          postgres    false    203   �       �          0    24641    historyauto 
   TABLE DATA           s   COPY public.historyauto (idhistauto, idauto, idkunde, abholdatum, ruckgabedatum, created_at, idmieten) FROM stdin;
    public          postgres    false    200   2       �          0    24713    kunde 
   TABLE DATA           i   COPY public.kunde (idkunde, vorname, name, stadt, alter, idnummer, fnummer, email, password) FROM stdin;
    public          postgres    false    205   O       �          0    24651    mieten 
   TABLE DATA           b   COPY public.mieten (idmieten, idauto, idkunde, abholdatum, ruckgabedatum, created_at) FROM stdin;
    public          postgres    false    201   �        �          0    24749    payment 
   TABLE DATA           i   COPY public.payment (idpayment, idauto, idmieten, idkunde, preis, ruckgabedatum, abholdatum) FROM stdin;
    public          postgres    false    207   v!       �           0    0    auto_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.auto_id_seq', 1, true);
          public          postgres    false    204            �           0    0    kunde_idkunde_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.kunde_idkunde_seq', 11, true);
          public          postgres    false    206            �           0    0    payment_idpayment_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.payment_idpayment_seq', 8, true);
          public          postgres    false    208            �           0    0    rent_idmieten_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.rent_idmieten_seq', 35, true);
          public          postgres    false    202            =           2606    24668    historyauto historyauto_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.historyauto
    ADD CONSTRAINT historyauto_pkey PRIMARY KEY (idhistauto);
 F   ALTER TABLE ONLY public.historyauto DROP CONSTRAINT historyauto_pkey;
       public            postgres    false    200            A           2606    24717    kunde kunde_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.kunde
    ADD CONSTRAINT kunde_pkey PRIMARY KEY (idkunde);
 :   ALTER TABLE ONLY public.kunde DROP CONSTRAINT kunde_pkey;
       public            postgres    false    205            C           2606    24753    payment payment_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.payment
    ADD CONSTRAINT payment_pkey PRIMARY KEY (idpayment);
 >   ALTER TABLE ONLY public.payment DROP CONSTRAINT payment_pkey;
       public            postgres    false    207            ?           2606    24672    mieten rent_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.mieten
    ADD CONSTRAINT rent_pkey PRIMARY KEY (idmieten);
 :   ALTER TABLE ONLY public.mieten DROP CONSTRAINT rent_pkey;
       public            postgres    false    201            �   �  x��V�n�0<�_��𑪅*�������Ay�*__'
��H��h���z���x�
#B���-Ѓ6�2�n�0���B�"K"�d9��"�`��Q�dcJ<Ҽ�M�l彖�t���%�rq~u�"�I��/�|Ճ�z�O�qN��b܅sð�s5�1�jy��~�{AZ���7 �ӬHJ�j���M��9��<�{�`��*��������fm��ͭ�B繄�Q`�/Sw����@OӁװ��h��ɀ��=Kc6J'�n�i��iԋ��i^�B�R�T?��?8�A}�O��v��=������y�Ҝ`�u��(�{����Û���h�p4�\�wFV�{l�=��I����{�V�rt ���r�1�Z�r      �      x������ � �      �   k  x�ݔMn�0���S�Q��Ovm�T�=A6��0Ȅ(��k"Hw�:��1��o<��u������[~h�J�MT(�}�G�<<��xd��*I�f�	����oMm�7��P�	�ч�֘G��0I3N�;��>�$t�+E
�uUOVkRG��;��Z��J%��s��z�I~M���z�����1��3<z<��nnr�lJ��Қr����������l��!�
�CO�;Rv�l2Rٶ#ݐ�Ðg�by���4P���A�f��	���DX�mʹ\*��{�-=�f��&���2�z�);{I�r~d����*�"r:⹴�}|P ��?����e�lW�֌��ǀ�      �   �   x����1D��G��m"l�udr�x%G��()�Ĵ��t=�}�_ڑ��[ʞ2�ޱ)w�X��a��%�:@+�)cr�8՟�E�!gK$�t֒1xMd�/[��.���I9�Fʞ\cǝ��57�b� 뗔6�XH����/��ke      �   p   x�}λ�0���>���Kx�?���T$��PP !���`��Hs\�"q���-:a�=c	*G�z�<���a'K�W�" n��1��z!����8�~�=����/�     