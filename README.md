# Ariane

Atom & RSS parser for Clojure. Modified to post to a flipthis-webhook running on 127.0.0.1:5000

## Usage

Supports RSS 2.0

e.g.

     user=> (use 'ariane.core)

     nil

    user=> (ariane.core/parse "http://your-rss-feed-here.com/feed/")

OR now added

    lein run

## More setup of the webhook for a Mac

### flipthis-webhook setup “server”

more info: https://github.com/tony2nite/flipthis-webhook

    mac:src username$ cd flipthis-webhook
    mac:flipthis-webhook username$ source ~/.profile
    mac:flipthis-webhook username$ brew install python --with-brewed-openssl
    ⇒
    mac:flipthis-webhook username$ sudo pip install --upgrade setuptools
    mac:flipthis-webhook username$ sudo pip install --upgrade pip
    running

    mac:flipthis-webhook username$ virtualenv venv
    New python executable in venv/bin/python2.7

    mac:flipthis-webhook username$ source venv/bin/activate
    (venv)mac:flipthis-webhook username$ pip install -r requirements.txt

    (venv)mac:flipthis-webhook username$ export FLIPBOARD_USERNAME='yourflipboardusername'
    (venv)mac:flipthis-webhook username$ export FLIPBOARD_PASSWORD='yourflipboardpassword'

    (venv)mac:flipthis-webhook username$ python webapp.py
    * Running on http://127.0.0.1:5000/
    * Restarting with reloader

### “ariane client”
    mac:src username$ cd ~/src/ariane

### run
    lein repl (or Start Clojure Console in intellij)
    user=> (use 'ariane.core)
    nil
    user=> (ariane.core/parse "yourRSSURLhere")
    {:infos nil, :entries ({:orig-content-encoding nil, :trace-redirects ["http://127.0.0.1:5000"], :r...

### “Or, with new scheduling”
    mac:src username$ cd ~/src/ariane
    mac:src username$ lein run

## License

Original Copyright © 2013 Eric Prunier

Distributed under the Eclipse Public License, the same as Clojure.
