# Ariane

Atom & RSS parser for Clojure. Modified to post to a flipthis-webhook running on 127.0.0.1:5000

## Usage

Supports RSS 2.0

e.g.

     user=> (use 'ariane.core)

     nil

    user=> (ariane.core/parse "http://your-rss-feed-here.com/feed/")

## License

Original Copyright © 2013 Eric Prunier

Distributed under the Eclipse Public License, the same as Clojure.
