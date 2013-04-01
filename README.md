play-jade-module
================

[1.2.5] primitive jade plugin

#### Usage
`#{jade.print 'index.jade', context:[youAreUsingJade:isTrue,pageTitle:'oh gosh!']/}`

##### View
`doctype 5
html(lang="en")
  head
    title= pageTitle
    script(type='text/javascript')
      console.log('tatam!');
  body
    h1 Jade - node template engine
    #container
      if youAreUsingJade
        p You are amazing
      else
        p Get on it!`