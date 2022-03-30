
# Introduction

Based on github webhook (subscribe every event on leetcode-Team repo) + wechat work api (push notification)


# Getting Started 

## Deploy server
mvn package appengine:deploy

## Check log
gcloud app logs tail -s default

## Config
$ gcloud config list

[accessibility]

screen_reader = True

[core]

account = colinqu2273@gmail.com

disable_usage_reporting = True

project = level-array-303722

test


