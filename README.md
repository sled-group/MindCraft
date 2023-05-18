# MindCraft

Authors: [Cristian-Paul Bara](https://sled.eecs.umich.edu/author/cristian-paul-bara/)\*, [Sky CH-Wang](https://skywang.me)\*, [Joyce Chai](https://web.eecs.umich.edu/~chaijy/)

This is the official code repository for the paper ([anthology link](https://aclanthology.org/2021.emnlp-main.85/)): 
> Cristian-Paul Bara, Sky CH-Wang, and Joyce Chai. 2021. MindCraft: Theory of Mind Modeling for Situated Dialogue in Collaborative Tasks. In *Proceedings of the 2021 Conference on Empirical Methods in Natural Language Processing (EMNLP)*.

```
@inproceedings{bara-etal-2021-mindcraft,
    title = "{M}ind{C}raft: Theory of Mind Modeling for Situated Dialogue in Collaborative Tasks",
    author = "Bara, Cristian-Paul  and
      CH-Wang, Sky  and
      Chai, Joyce",
    booktitle = "Proceedings of the 2021 Conference on Empirical Methods in Natural Language Processing",
    month = nov,
    year = "2021",
    address = "Online and Punta Cana, Dominican Republic",
    publisher = "Association for Computational Linguistics",
    url = "https://aclanthology.org/2021.emnlp-main.85",
    pages = "1112--1125",
    abstract = "An ideal integration of autonomous agents in a human world implies that they are able to collaborate on human terms. In particular, theory of mind plays an important role in maintaining common ground during human collaboration and communication. To enable theory of mind modeling in situated interactions, we introduce a fine-grained dataset of collaborative tasks performed by pairs of human subjects in the 3D virtual blocks world of Minecraft. It provides information that captures partners{'} beliefs of the world and of each other as an interaction unfolds, bringing abundant opportunities to study human collaborative behaviors in situated language communication. As a first step towards our goal of developing embodied AI agents able to infer belief states of collaborative partners in situ, we build and present results on computational models for several theory of mind tasks.",
}
```

## Installation Instructions

This README assumes that the user is about to set up the MindCraft task on a to-be-newly-created _Ubuntu-based AWS EC2 Server_. If not, some commands may be invalid (e.g. `apt-get` vs. `apt`). This has been tested on both Ubuntu versions 18 and 20.

__Server Setup & Port Forwarding__ (for reference, AWS port forwarding guidelines are adapted from [here](https://medium.com/@sumekenov/how-to-launch-minecraft-server-on-aws-7f4b9f7febf7)):

1. Launch an EC2 Instance, choosing an _Ubuntu_-based x86 Amazon Machine Image such as __Ubuntu Server 20.04 LTS (HVM), SSD Volume Type__.
2. Choose an instance type. Minimum resource requirements are relatively high, seeing as we are going to run web & game servers concurrently on the same machine. Testing has indicated that instances like `t2.xlarge` with at least 16 gigs of RAM work fine.
3. Leave the options specified in `3. Configure Instance`, `4. Add Storage`, and `5. Add Tags` as default.
4. On `6. Configure Security Group`, `Add Rule` of type `Custom TCP Rule`, with options `Port Range: 22565` and `Source: Anywhere`. This ensures that players can access the game server with just the IP address.
5. Add another rule, this time with option `Port Range: 8080`, keeping all other options the same as above. This ensures that players can access the web server with just the IP address.
6. Review and launch, specifying your EC2 KeyPair for remote admin access.

__Required Depenencies__ (install these `ssh`ed into the EC2 machine):

1. __Java__ (for reference, Java-Spigot guidelines are adapted from [here](https://www.spigotmc.org/wiki/buildtools/#linux))
   1. First, update your local package lists with `sudo apt update`; this updates the URL locations for all the required dependencies you're going to install later. On a newly-created EC2 server, these lists at startup are going to be _horribly_ out of date.
   2. Next, install Java Runtime Environment 8 with `sudo apt install openjdk-8-jre-headless`.
2. __MySQL__ (for reference, MySQL guidelines are adapted from [here](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-18-04))
   1. To install MySQL server, run `sudo apt install mysql-server`.
   2. Run setup script `sudo mysql_secure_installation` to configure your newly installed server with authentication permissions. Go through the setup instructions, setting up a password for the `root`-user, and confirming other security settings.
   3. To confirm that your newly-installed MySQL server is running, run `systemctl status mysql.service` to manually check.
   4. Now, the password just created in step 2.2 doesn't actually enable remote connections as `root` (which we want for our game server & web server); here, execute:
      1. `sudo mysql`
      2. In the MYSQL commandline, run `ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';` (replacing password with a password of your choosing).
      3. And finally `FLUSH PRIVILEGES;` to write changes to disk.
      4. To exit the MySQL commandline, `exit`.
   5. To create your MySQL database (where all MindCraft user activity is recorded), enter the MySQL commandline this time with `mysql -u root -p`, and then entering the password you just created.
   6. In the commandline interface, `CREATE DATABASE minecraft;` to create, and then `SHOW DATABASES;` to manually confirm the creation of the `minecraft` database. Before you `exit`, run `SHOW GLOBAL VARIABLES LIKE 'PORT';` to confirm the port that the MySQL server is running on (by default, it should be 3306).
   7. Finally, set up game server plugin authentication details! Before you proceed: if this is your first time setting this up, the files listed below may not have been created yet. To create these files, go ahead and start the server once with `bash startServer.sh`. You're going to see a _ton_ of errors appear, but hold out for now! When the server is done spinning up, enter `stop` to stop the server, and then proceed onto these sub-steps.
      1. Open `spigot/plugins/situatedDialogue/config.yml`, and change:
         1. `mysql_password` to what you defined in 2.4.2.
         2. `mysql_port` to what you just confirmed in 2.4.6 (default is 3306).
      2. Open `spigot/plugins/LogBlock/config.yml`, and change the same lines as above, this time under section `mysql`. Remember to also change the MySQL user here to `root` (default initialization has it as something else)!
      3. Open `mean/server.js` and do the same for MYSQL authentication credentials in the first few lines.
      4. Open `spigot/plugins/AdvancedReplay` and do the same for `mysql.yml`.
3. __NPM__ (for reference, Node.JS guidelines are adapted from [here](https://linuxize.com/post/how-to-install-node-js-on-ubuntu-18.04/))
   1. Package lists should already be up-to-date, so running `sudo apt install nodejs` and `sudo apt install npm` will suffice; this will install the latest versions.
4. __MongoDB__ (for reference, Mongo guidelines are adapted from [here](https://medium.com/faun/install-mongodb-on-aws-ubuntu-ec2-instance-6794cd8e3b4e)) These steps are necessary mainly because I have used a cookie cutter MEAN stack setup, even though the underlying web server doesn't _really_ use Mongo at _all_.
   1. Run `wget -qO - https://www.mongodb.org/static/pgp/server-4.2.asc | sudo apt-key add -`
   2. Run `echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/4.2 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.2.list`
   3. Run `sudo apt update` and `sudo apt install -y mongodb-org` to install Mongo.
   4. Run `sudo systemctl start mongod` to start the service and `sudo systemctl status mongod` to check service status.
   5. Finally, run `sudo systemctl enable mongod` to enable Mongo to start on every server reboot.

## File Structure

The MindCraft environment is split into __three__ main modules: (1) _initialization_, by which task variables (e.g. number of games, complexity of games, and more) are set to customized or default values; (2) _the game server_, a [Bukkit/Spigot](https://dev.bukkit.org/)-made Minecraft multiplayer server that hosts the game itself and records all in-game interactions; and (3) _the web server_, a MEAN-stack (mainly, Node.JS) web server that records webpage user interactions, where currently recording of player mental states takes place.

All recording of user data -both game server and through the web server- are consolidated into a local MySQL database, the authentication details of which are to be specifiied in initialization files (or left as default).

## Execution

Both the game server and web server are designed to be run concurrently in parallel. To achieve this, set up two `tmux`es and run the following sections in separate muxes.

__Game Server__: Run `bash startServer.sh`. Edit the parameters passed to the plan generator python script if desired before running the server. If it's your first time running the server, you _may_ have to go the newly-generated `spigot/eula.txt` and change `eula=false` to `eula=true`.
\
__Web Server__: Navigate to the `mean` folder and run `npm start`. Do this _after_ the game server has been successfully spun up.

## Replay

For replaying a specific previous game, make sure that the correct __plan file__ of the logged game, indicated in the format `logs/logs.XXXXXXX.plan.json` (where XXX is the UNIX time stamp of when the game was played), is copied to the following folder and named as `plan_generator/plan.json`. After this has been done, start the server with `bash spigot/start.command` instead of the usual bash file. 

## Dataset

[Data](https://huggingface.co/datasets/sled-umich/MindCraft)
[//]: [Data](https://www.dropbox.com/s/ii0i5f3ufharefa/mindcraft_dataset.zip?dl=0)


[Models](https://www.dropbox.com/s/u8cqi3nmz2zydoq/mindcraft_models.zip?dl=0)

If you are having trouble accessing the data and models please contact [Cristian-Paul Bara](https://sled.eecs.umich.edu/author/cristian-paul-bara/) at [cpbara@umich.edu](cpbara@umich.edu)
