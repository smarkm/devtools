# Soox eclipse plugin

## Install
* [Download](https://github.com/smarkm/devtools/releases) soox jar file
* Put the soox jar to `eclipse/dropins`
* Restart eclipse

## Configuration
You can edit the `engines.properties` in `eclipse/configuration/org.smark.soox.eclipse` folder,customize your own search engine.  
The content like this:  
  
	d=https://www.baidu.com/s?wd=
	3=https://www.so.com/s?q=
	b=http://www.bing.com/search?q=
	g=https://www.google.com/search?q=
	s=http://stackoverflow.com/search?q=
	y=https://search.yahoo.com/search?p=
	m=http://mvnrepository.com/search?q=
	w=http://www.wolframalpha.com/input/?i=


## Changes
### 0.0.5
* Binding default hotkey `CTRL+SHIFT+Z`
* Fix `google` search url