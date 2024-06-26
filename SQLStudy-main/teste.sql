PGDMP      :                |            exemplo1    16.3    16.3     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16398    exemplo1    DATABASE        CREATE DATABASE exemplo1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE exemplo1;
                postgres    false            �            1259    16407    cliente    TABLE     �   CREATE TABLE public.cliente (
    id_cliente integer NOT NULL,
    nome character varying(255) NOT NULL,
    cpf character varying(15)
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    16406    cliente_id_cliente_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cliente_id_cliente_seq;
       public          postgres    false    218            �           0    0    cliente_id_cliente_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;
          public          postgres    false    217            �            1259    16400    produto    TABLE     �   CREATE TABLE public.produto (
    id_produto integer NOT NULL,
    descricao character varying(255) NOT NULL,
    valor money NOT NULL
);
    DROP TABLE public.produto;
       public         heap    postgres    false            �            1259    16399    produto_id_produto_seq    SEQUENCE     �   CREATE SEQUENCE public.produto_id_produto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.produto_id_produto_seq;
       public          postgres    false    216            �           0    0    produto_id_produto_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.produto_id_produto_seq OWNED BY public.produto.id_produto;
          public          postgres    false    215            �            1259    16416    venda    TABLE     g   CREATE TABLE public.venda (
    id_venda integer NOT NULL,
    cliente integer,
    produto integer
);
    DROP TABLE public.venda;
       public         heap    postgres    false            �            1259    16415    venda_id_venda_seq    SEQUENCE     �   CREATE SEQUENCE public.venda_id_venda_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.venda_id_venda_seq;
       public          postgres    false    220            �           0    0    venda_id_venda_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.venda_id_venda_seq OWNED BY public.venda.id_venda;
          public          postgres    false    219            %           2604    16410    cliente id_cliente    DEFAULT     x   ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);
 A   ALTER TABLE public.cliente ALTER COLUMN id_cliente DROP DEFAULT;
       public          postgres    false    218    217    218            $           2604    16403    produto id_produto    DEFAULT     x   ALTER TABLE ONLY public.produto ALTER COLUMN id_produto SET DEFAULT nextval('public.produto_id_produto_seq'::regclass);
 A   ALTER TABLE public.produto ALTER COLUMN id_produto DROP DEFAULT;
       public          postgres    false    216    215    216            &           2604    16419    venda id_venda    DEFAULT     p   ALTER TABLE ONLY public.venda ALTER COLUMN id_venda SET DEFAULT nextval('public.venda_id_venda_seq'::regclass);
 =   ALTER TABLE public.venda ALTER COLUMN id_venda DROP DEFAULT;
       public          postgres    false    220    219    220            �          0    16407    cliente 
   TABLE DATA           8   COPY public.cliente (id_cliente, nome, cpf) FROM stdin;
    public          postgres    false    218   �       �          0    16400    produto 
   TABLE DATA           ?   COPY public.produto (id_produto, descricao, valor) FROM stdin;
    public          postgres    false    216   �       �          0    16416    venda 
   TABLE DATA           ;   COPY public.venda (id_venda, cliente, produto) FROM stdin;
    public          postgres    false    220   �       �           0    0    cliente_id_cliente_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 1, false);
          public          postgres    false    217            �           0    0    produto_id_produto_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.produto_id_produto_seq', 1, false);
          public          postgres    false    215            �           0    0    venda_id_venda_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.venda_id_venda_seq', 1, false);
          public          postgres    false    219            *           2606    16414    cliente cliente_cpf_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cpf_key UNIQUE (cpf);
 A   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_cpf_key;
       public            postgres    false    218            ,           2606    16412    cliente pk_cliente 
   CONSTRAINT     X   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (id_cliente);
 <   ALTER TABLE ONLY public.cliente DROP CONSTRAINT pk_cliente;
       public            postgres    false    218            (           2606    16405    produto pk_produto 
   CONSTRAINT     X   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT pk_produto PRIMARY KEY (id_produto);
 <   ALTER TABLE ONLY public.produto DROP CONSTRAINT pk_produto;
       public            postgres    false    216            .           2606    16421    venda pk_venda 
   CONSTRAINT     R   ALTER TABLE ONLY public.venda
    ADD CONSTRAINT pk_venda PRIMARY KEY (id_venda);
 8   ALTER TABLE ONLY public.venda DROP CONSTRAINT pk_venda;
       public            postgres    false    220            /           2606    16422    venda fk_cliente    FK CONSTRAINT     y   ALTER TABLE ONLY public.venda
    ADD CONSTRAINT fk_cliente FOREIGN KEY (cliente) REFERENCES public.cliente(id_cliente);
 :   ALTER TABLE ONLY public.venda DROP CONSTRAINT fk_cliente;
       public          postgres    false    220    218    4652            0           2606    16427    venda fk_produto    FK CONSTRAINT     y   ALTER TABLE ONLY public.venda
    ADD CONSTRAINT fk_produto FOREIGN KEY (produto) REFERENCES public.produto(id_produto);
 :   ALTER TABLE ONLY public.venda DROP CONSTRAINT fk_produto;
       public          postgres    false    216    220    4648            �      x������ � �      �      x������ � �      �      x������ � �     