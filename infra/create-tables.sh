#!/bin/bash

ENDPOINT="http://localhost:8000"

echo "Creando tabla users..."

aws dynamodb create-table \
  --table-name users \
  --attribute-definitions \
      AttributeName=id,AttributeType=S \
  --key-schema \
      AttributeName=id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --endpoint-url $ENDPOINT

echo "Creando tabla groups..."

aws dynamodb create-table \
  --table-name groups \
  --attribute-definitions \
      AttributeName=id,AttributeType=S \
  --key-schema \
      AttributeName=id,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --endpoint-url $ENDPOINT

echo "Creando tabla areas..."

aws dynamodb create-table \
  --attribute-definitions \
      AttributeName=id,AttributeType=S \
  --key-schema \
      AttributeName=id,KeyType=HASH \
  --table-name areas \
  --billing-mode PAY_PER_REQUEST \
  --endpoint-url $ENDPOINT

echo "Creando tabla tickets..."

aws dynamodb create-table \
  --table-name tickets \
  --attribute-definitions \
      AttributeName=id,AttributeType=S \
      AttributeName=assignedToId,AttributeType=S \
      AttributeName=requesterId,AttributeType=S \
      AttributeName=targetGroupId,AttributeType=S \
      AttributeName=status,AttributeType=S \
      AttributeName=priority,AttributeType=S \
      AttributeName=created,AttributeType=S \
  --key-schema \
      AttributeName=id,KeyType=HASH \
  --global-secondary-indexes \
  '[
      {
          "IndexName": "assigned-index",
          "KeySchema": [
              {
                  "AttributeName": "assignedToId",
                  "KeyType": "HASH"
              },
              {
                  "AttributeName": "created",
                  "KeyType": "RANGE"
              }
          ],
          "Projection": {
              "ProjectionType": "ALL"
          }
      },
      {
          "IndexName": "requester-index",
          "KeySchema": [
              {
                  "AttributeName": "requesterId",
                  "KeyType": "HASH"
              },
              {
                  "AttributeName": "created",
                  "KeyType": "RANGE"
              }
          ],
          "Projection": {
              "ProjectionType": "ALL"
          }
      },
      {
          "IndexName": "group-index",
          "KeySchema": [
              {
                  "AttributeName": "targetGroupId",
                  "KeyType": "HASH"
              },
              {
                  "AttributeName": "created",
                  "KeyType": "RANGE"
              }
          ],
          "Projection": {
              "ProjectionType": "ALL"
          }
      },
      {
          "IndexName": "status-index",
          "KeySchema": [
              {
                  "AttributeName": "status",
                  "KeyType": "HASH"
              },
              {
                  "AttributeName": "created",
                  "KeyType": "RANGE"
              }
          ],
          "Projection": {
              "ProjectionType": "ALL"
          }
      },
      {
          "IndexName": "priority-index",
          "KeySchema": [
              {
                  "AttributeName": "priority",
                  "KeyType": "HASH"
              },
              {
                  "AttributeName": "created",
                  "KeyType": "RANGE"
              }
          ],
          "Projection": {
              "ProjectionType": "ALL"
          }
      }
  ]' \
  --billing-mode PAY_PER_REQUEST \
  --endpoint-url $ENDPOINT

echo "Tablas creadas correctamente."