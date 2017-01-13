#!/bin/bash
: ${DB_NAME:=elips}
: ${POSTGRES_PASSWORD=elips}
: ${DB_PG_DUMP_FILE:=/docker-entrypoint-initdb.d/elips.sql}


gosu postgres postgres --single -E <<-EOSQL
				CREATE DATABASE ${DB_NAME} ;
			EOSQL

gosu postgres pg_ctl start -w 

if [ -f ${DB_PG_DUMP_FILE} ]; then
echo "Chargement du DUMP"
gosu postgres psql -d "$DB_NAME" -U "$POSTGRES_USER" <"$DB_PG_DUMP_FILE"
echo "Fin de chargement du DUMP"
fi

gosu postgres pg_ctl stop -w
				
