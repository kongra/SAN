#!/bin/sh

stack exec hlint `find src/  -name "*.hs"`
stack exec hlint `find app/  -name "*.hs"`
stack exec hlint `find test/ -name "*.hs"`
