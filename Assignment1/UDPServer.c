#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define ECHOMAX 255  // Maximum size of echo datagram

void DieWithError(char *errorMessage) {
    perror(errorMessage);
    exit(EXIT_FAILURE);
}

int main(int argc, char *argv[]) {

    if (argc != 2) {  // Test for correct argument list
        fprintf(stderr, "Usage: %s <UDP Port>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    in_port_t servPort = atoi(argv[1]);

    int sockfd;
    if ((sockfd = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP)) < 0)
        DieWithError("socket() failed");

    struct sockaddr_in servAddr;
    memset(&servAddr, 0, sizeof(servAddr));
    servAddr.sin_family = AF_INET;
    servAddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servAddr.sin_port = htons(servPort);

    if (bind(sockfd, (struct sockaddr *) &servAddr, sizeof(servAddr)) < 0)
        DieWithError("bind() failed");

    struct sockaddr_in clntAddr;
    unsigned int clntAddrLen = sizeof(clntAddr);
    char echoBuffer[ECHOMAX];

    for (;;) {  // Run forever, receiving and echoing datagrams
        ssize_t numBytes = recvfrom(sockfd, echoBuffer, ECHOMAX, 0,
            (struct sockaddr *) &clntAddr, &clntAddrLen);
        if (numBytes < 0)
            DieWithError("recvfrom() failed");

        printf("Handling client at %s on port %d\n",
               inet_ntoa(clntAddr.sin_addr), ntohs(clntAddr.sin_port));

        if (sendto(sockfd, echoBuffer, numBytes, 0,
            (struct sockaddr *) &clntAddr, sizeof(clntAddr)) != numBytes)
            DieWithError("sendto() failed");
    }
    // close(sockfd); // Unreachable code as the loop is infinite
    return 0;
}