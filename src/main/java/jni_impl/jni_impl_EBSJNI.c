/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
#include <string.h> 
#define soundso "./libsqc.so"
#define photoso "./libBKKAdaptor.so"
#define soundconfig "./cv_configuration.json"
#define photoconfig "./ci_configuration.json"
#define soundindex 0
#define photoindex 1

#ifndef _Included_jni_impl_EBSJNI
#define _Included_jni_impl_EBSJNI

typedef struct
{
  int major;
  int minor;
  int build;
} Version;

typedef struct
{
  Version version;
  char* id;
  void* payment;
  int last_error;
} Session;

typedef struct
{
  uint8_t* content;
  uint64_t size;
}   ContentInfo;

typedef int (*create_session)(struct Session*  , char*);
typedef int (*BKKCheck)(Session*,  uint8_t* , uint64_t);

typedef int (*BKK)(Session *,  char *path_to_config);
typedef int (*i_check_format)(Session *,  uint8_t * , uint64_t);

typedef struct
{
  void(*Checker)(void);    
  void* handles[2];
  Session* sessions[2];
  BKKCheck v_check;
  BKKCheck i_check;
  ContentInfo*(*loadContent)(char * filename);
  void (*initSessions)(struct Checker*);
  Session* (*initSession)(void*, char*, char*);
  void (*loadcheckers)(struct Checker*);
  int (*checkFile)(struct Checker*, char*);
  void (*foreach)(struct Checker*, char*);
} Checker;

Checker* CheckerPtr;
int read_file_content(const char* file_path, uint8_t** content, size_t* content_size);
Session* initSession(void* handle, char* symbol, char* config);
void initSessions_(Checker* self);
void loadscheckers_(Checker* self);
int checkFile_(Checker* self, char* filename);
Checker* Checker__();
int checkin(char * filename);

Session* initSession(void* handle, char* symbol, char* config)
{
  Session* sess = (Session*)malloc(sizeof(Session));
  create_session load = (create_session)(dlsym(handle, symbol));
  if (!load){
    printf("error loading %s\n\n\n", symbol );
    sess=NULL;
  }
  sess->id="my_session";
  if (!load(sess, config)){
    printf("error create session\n\n");
    sess=NULL;
    return sess;
  }
  printf("Session creates succesfully\n\n");
  return sess;
}

ContentInfo* loadContent(char * filename)
{
  ContentInfo* ci=(ContentInfo*)malloc(sizeof(ContentInfo));
  if (read_file_content(filename, &ci ->content, &ci->size))
    return ci;
  freeMem(ci);
  return NULL;
}

int read_file_content(const char* file_path, uint8_t** content, size_t* content_size)
{
  FILE* fd = fopen(file_path, "rb");
  if (fd == NULL)
  {
    fprintf(stderr, "file \"%s\" not found\n", file_path);
    return 0;
  }
  fseek(fd, 0L, SEEK_END);
  (*content_size) = (size_t) ftell(fd);
  rewind(fd);
  (*content) = (uint8_t*) calloc(1, (*content_size));
  fread((*content), (*content_size), 1, fd);
  fclose(fd);
  return 1;
}

void foreach_(Checker* self, char * filename)
{
    FILE* fp;
    char buf[1024];
    if ((fp = fopen(filename, "r")) == NULL)
    {
        perror("fopen source-file");
        return;
    }

    while (fgets(buf, sizeof(buf), fp) != NULL)
    {
        buf[strlen(buf) - 1] = '\0'; // eat the newline fgets() stores
        self->checkFile(self, buf);
    }
    fclose(fp);
    return ;
}

void freeMem(ContentInfo* ci)
{
  free(ci->content);
  free(ci);
}

int checkFile_(Checker* self, char* filename)
{
  ContentInfo* ci = self->loadContent(filename);;
  if (ci == NULL)
      return -1;

  if (strstr(filename, "wav")!=NULL){
    printf("CHECKING WAV FILE %s", filename);
    if (!self->v_check(self->sessions[soundindex], ci->content, ci->size))
    {
	freeMem(ci);
	printf("Check failed!\n");
	return -3;
    }
    else
    {
      printf("Checking passed\n");
      freeMem(ci);
      return 0;
    }
  }
  printf("CHECKING photo FILE %s", filename);
  if (!self->i_check(self->sessions[photoindex], ci->content, ci->size))
  {
      printf("Check failed!\n");
      freeMem(ci);
      return -3;
  }
  else
  {
      printf("Checking passed\n");
      freeMem(ci);
      return 0;
  }
  freeMem(ci);
  return -9;

}

void initSessions_(Checker* self)
{
  self->sessions[photoindex]=self->initSession(self->handles[photoindex], "i_create_session", photoconfig);
  self->sessions[soundindex]=self->initSession(self->handles[soundindex], "v_create_session", soundconfig);
  int i;
  for (i=0;i<2; i++){
    if (self->sessions[i]==NULL)
      printf("Error create session #%d\n", i);
  }
}

void loadscheckers_(Checker* self){
  self->i_check = (BKKCheck)(dlsym(self->handles[photoindex],"i_check_format"));
  self->v_check = (BKKCheck)(dlsym(self->handles[soundindex],"v_check"));
  if (!self->i_check)
    printf("error load i_check");
  if (!self->v_check)
    printf("error load v_check");

}

Checker* Checker__()
{
  Checker* res = (Checker*)malloc(sizeof(Checker));
  res->loadContent = loadContent;
  res->handles[soundindex] = dlopen(soundso, RTLD_LAZY);
  res->handles[photoindex] = dlopen(photoso, RTLD_LAZY);

  if (!res->handles[soundindex])
    printf("error loading sound so");
  else
    printf("Load success sound so");

  if (!res->handles[photoindex])
    printf("error loading photo so");
  else
    printf("Load success photo so");

  res->initSession=initSession;
  res->initSessions=initSessions_;
  res->loadcheckers=loadscheckers_;
  res->loadContent=loadContent;
  res->checkFile = checkFile_;
  res->foreach=foreach_;
  res->initSessions(res);
  res->loadcheckers(res);
  return res;
}

void foreachGlobal(char * filename)
{
  printf("CALL  GLOBAL FOREACH>>\n\n");
  foreach_(CheckerPtr, filename);
}

int checkin(char * filename)
{
  int res;
  Checker* initial = Checker__();
  res = initial ->checkFile(initial, filename);
  free(initial);
  return res;

}


void initGlobal()
{
  CheckerPtr = Checker__();
}


int checkFileGlobal(char * filename)
{
  return CheckerPtr ->checkFile(CheckerPtr, filename);
}



int lets_check(char * filename){
  Checker* chk = Checker__();
  int result___ = checkFile_(chk, filename);
  free(chk);
  return result___;

};






int ret0(){
  return 0;
}

int ret1(){
  return 1;
}

int ret_1(){
  return -1;
}

int ret_3(){
  return -3;
}




JNIEXPORT jint JNICALL Java_jni_1impl_EBSJNI_summ
  (JNIEnv* env, jobject thisObject, jint a, jint b){
    printf("CALCULATING SUMM\n\n\n");
    return a+b;
	
}


JNIEXPORT void JNICALL Java_jni_1impl_EBSJNI_init
  (JNIEnv* env, jobject thisObject){
	CheckerPtr = Checker__();
}



JNIEXPORT jint JNICALL Java_jni_1impl_EBSJNI_checkFile
  (JNIEnv* env, jobject thisObject, jstring filename){
    const char *fn = (*env)->GetStringUTFChars(env,thisObject,0);
	return lets_check(fn);
}
#endif

