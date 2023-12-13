
# Acceptance Test

Also known as a black box test. The idea is that these test only test scenario's you can become through calling the 
endpoint. Testing the full flow by bringing up the full spring boot context.

Each test should use a or multiple TestClient(s) to interact with the application.
By using a real postgres database (with testcontainers) we also test the real scenario and not using an in memory DB. 