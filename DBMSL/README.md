# DBMS
## Steps to Install MySQL on Ubuntu
Update your repositories using:

`sudo apt-get update`

To install the latest version of MySQL Server, run:

`sudo apt-get install mysql-server`

Then

`sudo apt-get install mysql-client`

After the installation is done, run the following command to start the MySQL service:

`sudo mysql`

If you encounter any error in this step, try manually starting the MySQL service using:

`sudo systemctl start mysql`

To check the status of MySQL service, run:

`sudo systemctl status mysql`

To stop MySQL, run:

`sudo systemctl stop mysql`

To restart the MySQL service, run:

`sudo systemctl restart mysql`

## Steps to Install MongoDB 7.0 on Ubuntu

Run the following commands one after the other:

`sudo apt-get install gnupg curl`

`curl -fsSL https://pgp.mongodb.com/server-7.0.asc | \sudo gpg -o /usr/share/keyrings/mongodb-server-7.0.gpg \--dearmor`

If you get a warning to overwrite the configuration file, press Y

If you don't know already, use the following command to check your Ubuntu version:

`lsb_release -a`

For Ubuntu 20.04 (LTS)

`echo "deb [ arch=amd64,arm64 signed-by=/usr/share/keyrings/mongodb-server-7.0.gpg ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/7.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-7.0.list`

For Ubuntu 22.04 (LTS)

`echo "deb [ arch=amd64,arm64 signed-by=/usr/share/keyrings/mongodb-server-7.0.gpg ] https://repo.mongodb.org/apt/ubuntu jammy/mongodb-org/7.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-7.0.list`

Then, run:

`sudo apt-get update`

`sudo apt-get install -y mongodb-org`

`sudo systemctl start mongod`

If you receive an error similar to the following when starting mongod:

Failed to start mongod.service: Unit mongod.service not found.

Run the following command first:

`sudo systemctl daemon-reload`

`sudo systemctl status mongod`

You should see an active(running) status.

Type `q` to exit.

To start MongoDB server, type the following command:

`mongosh`
