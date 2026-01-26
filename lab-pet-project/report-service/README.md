# report-service

Reactive Report service that aggregates product names and delivery statuses from other services.

Endpoints:
- `POST /report` - accepts JSON array of product ids and returns list of `{id,name,deliveryStatus}`

Defaults:
- If product is not found it is skipped.
- If delivery status is missing, `UNKNOWN` is used.
