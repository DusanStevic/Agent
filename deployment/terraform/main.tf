terraform {
  backend "pg" {
  }
}

provider "heroku" {
}

variable "servers_app_name" {
  description = "Unique name of the Servers app"
}

resource "heroku_config" "prod" {
  vars = {
    STAGE = "PROD"
  }
}

resource "heroku_app" "servers1" {
  name   = var.servers_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "servers1" {
  app = heroku_app.servers1.id

  source {
    path = "servers"
  }
}

resource "heroku_app_config_association" "servers1" {
  app_id = heroku_app.servers1.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon" "database" {
  app  = heroku_app.servers1.name
  plan = "heroku-postgresql:hobby-dev"
}

resource "heroku_app" "servers2" {
  name   = "${var.servers_app_name}-2"
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "servers2" {
  app = heroku_app.servers2.id

  source {
    path = "servers"
  }
}

resource "heroku_app_config_association" "servers2" {
  app_id = heroku_app.servers2.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon_attachment" "database" {
  app_id  = heroku_app.servers2.id
  addon_id = heroku_addon.database.id
}


output "servers1_app_url" {
  value = "https://${heroku_app.servers1.name}.herokuapp.com"
}
output "servers2_app_url" {
  value = "https://${heroku_app.servers2.name}.herokuapp.com"
}