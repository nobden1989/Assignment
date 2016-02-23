#!/bin/bash

FTCOUNT=0
SIZE=0
TARGET=$(cd `dirname $0`; pwd)
DEST="/myArchive"
while [[ $# > 0 ]]
do
key="$1"
case $key in
    -s|--extension)
    SIZE="$2"
    shift # past argument
    ;;
    -t|--searchpath)
    TARGET="$2"
    shift # past argument
    ;;
    -d|--lib)
    DESTINATION="$2"
    shift # past argument
    ;;
    --default)
    DEFAULT=YES # file option
    ;;
    *)
    FILETYPE[$FTCOUNT]="$1"
    FTCOUNT=$[$FTCOUNT+1]
    ;;
esac
shift # past argument or value
done

if [[ -z $DESTINATION ]]; then
    DESTINATION=$TARGET
fi


if [[ ${TARGET} == ${DESTINATION} ]]; then
    DESTINATION=$TARGET$DEST
fi


echo FILE SIZE  = "${SIZE}"
echo COPY TARGET     = "${TARGET}"
echo COPY DESTINATION    = "${DESTINATION}"
echo FILE TYPE = "${FILETYPE[*]}"

for file in ${TARGET}/*; do  
    L=`du -b $file|awk '{print $1}'`
    for i in ${FILETYPE[@]}; do
        if [[ ${file##*.} == $i && $L < $SIZE ]]; then
	    test -d $DESTINATION || mkdir -p $DESTINATION && cp $file $DESTINATION
	fi
    done
done 
