#! /bin/sh

ACCESS_KEY="Access_Key_Here"
SECRET_KEY="Secret_Key_Here"
BUCKET="Bucket_name_here"

if [ -e /usr/bin/s3fs ]; then
    echo "s3fs is already installed."
else
    yum install -y make
    yum install -y gcc
    yum install -y gcc-c++
    yum install -y pkgconfig
    yum install -y fuse-devel
    yum install -y libxml2-devel
    yum install -y libcurl-devel
    yum install -y openssl-devel
    yum install -y java-1.6.0-openjdk-devel
    
    wget http://s3fs.googlecode.com/files/s3fs-r177-source.tar.gz
    tar xzf s3fs-r177-source.tar.gz
    
    cd s3fs
    make
    make install
    cd ..
    
    rm -f s3fs-r177-source.tar.gz
    rm -rf s3fs
fi

if [ -e "/mnt/$BUCKET" ]; then
    echo ""
else
    mkdir /mnt/$BUCKET
fi

if [ `grep -c "/mnt/$BUCKET" /etc/mtab` = "0" ]; then
    cd /etc/
    touch passwd-s3fs
    chmod 640 passwd-s3fs
    echo "$ACCESS_KEY:$SECRET_KEY" >> passwd-s3fs
    
    /usr/bin/s3fs $BUCKET /mnt/$BUCKET
fi

echo "This script has just installed:"
echo "  make, gcc, g++, pkg-config, fuse, libxml2, libcurl,"
echo "  openssl, the OpenJDK, and s3fs."
echo ""
echo "The bucket $BUCKET is mounted at:"
echo "  /mnt/$BUCKET"
echo "In the future, you can mount buckets with:"
echo "  /usr/bin/s3fs bucket_name mount_point"
echo "NOTE: Your access key and secret key are stored in"
echo "      plaintext in /etc/passwd-s3fs ."
