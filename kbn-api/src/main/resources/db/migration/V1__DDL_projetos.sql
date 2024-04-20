CREATE table if not exists public.tb_projetos (
    id uuid DEFAULT gen_random_uuid() not null,
    titulo varchar(100) not null,
    atualizado_em timestamp(6) null,
    criado_em timestamp(6) not null,
    status int2 not null default 1,
    tenant_id uuid NOT NULL,
    user_id uuid not null,
    CONSTRAINT usuario_status_check CHECK (((status >= 0) AND (status <= 1)))
);