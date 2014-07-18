# Ariane

RSS parser for Clojure. Modified to post to a Flipboard via a chromedriver
https://code.google.com/p/selenium/wiki/ChromeDriver plug-in.

[![Build Status](https://travis-ci.org/xicubed/ariane.png?branch=master)](https://travis-ci.org/xicubed/ariane)

## Usage

Supports RSS 2.0

e.g.

     user=> (use 'ariane.core)

     nil

    user=> (ariane.core/parse "http://your-rss-feed-here.com/feed/")

OR now added

    lein run

### run

    Setup environment variables...
    On a mac that's in the ~/.profile
    export FLIPBOARD_USERNAME=flipboardusername
    export FLIPBOARD_PASSWORD=flipboardpassword
    export FLIPBOARD_MAGAZINE=flipboardmagazine

    reload environment variables if necessary
    mac:src username$ source ~/.profile

    mac:src username$ cd ~/src/ariane
    mac:src username$ lein run

## License

Copyright © 2013 mal

Distributed under the Eclipse Public License, the same as Clojure.
