#!/bin/bash
echo "Create database script"

psql -h localhost -U postgres -c "drop database helpet"
psql -h localhost -U postgres -c "drop user if exists helpet"
psql -h localhost -U postgres -c "create user helpet with password 'helpet';"
psql -h localhost -U postgres -c "create database helpet with owner=helpet;"

CDDIR=$(cd $(dirname "$0"); pwd)
echo "Dir: "$CDDIR

echo "Create schema script"

psql -h localhost helpet -U postgres -c "DROP SCHEMA IF EXISTS public CASCADE;"

psql -h localhost helpet -U helpet < "$CDDIR/init_db.sql"
psql -h localhost helpet -U helpet < "$CDDIR/helpet.sql"