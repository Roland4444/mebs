gcc -c -fPIC -I/usr/lib/jvm/java-8-jdk/include -I/usr/lib/jvm/java-8-jdk/include/linux jni_impl_EBSJNI.c -o jni_impl_EBSJNI.o
gcc -shared -fPIC -o libuk.so jni_impl_EBSJNI.o -lc

