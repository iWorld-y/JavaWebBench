package com.wbjv;

/***
 * @author iWorld
 * @date 2021-9-27 11:09:54
 *
 * This is free software, see GNU Public License version 3 for
 * details.
 * Usage:
 *      webbench --help
 *
 * Return codes:
 *      0 - success
 *      1 - benchmark failed (server is not on-line)
 *      2 - bad param
 *      3 - internal error, fork faild
 *
 * */

public class Main {
    protected final static int MAXHOSTNAMELEN = 64;
    protected final static int METHOD_GET = 0;
    protected final static int METHOD_HEAD = 1;
    protected final static int METHOD_OPTIONS = 2;
    protected final static int METHOD_TRACE = 3;
    protected final static String PROGRAM_VERSION = "0.1";
    protected final static int REQUEST_SIZE = 2048;

    protected static int benchtime = 30;
    protected static int bytes = 0;
    protected static int clients = 1;
    protected static int failed = 0;
    protected static int force = 0;
    protected static int forceReload = 0;
    protected static int http10 = 0;
    protected static int method = METHOD_GET;
    protected static char[] proxyhost;
    protected static int proxyport = 80;
    protected static int speed = 0;
    protected static int timerexpired = 0;
    /* internal */
    int[] mypipe = new int[2];
    char[] host = new char[MAXHOSTNAMELEN];
    char[] request = new char[REQUEST_SIZE];

    public static void usage() {
        System.out.println("webbench [option]... URL\n" +
                "  -f|--force               Don't wait for reply from server.\n" +
                "  -r|--reload              Send reload request - Pragma: no-cache.\n" +
                "  -t|--time <sec>          Run benchmark for <sec> seconds. Default 30.\n" +
                "  -p|--proxy <server:port> Use proxy server for request.\n" +
                "  -c|--clients <n>         Run <n> HTTP clients at once. Default one.\n" +
                "  -9|--http09              Use HTTP/0.9 style requests.\n" +
                "  -1|--http10              Use HTTP/1.0 protocol.\n" +
                "  -2|--http11              Use HTTP/1.1 protocol.\n" +
                "  --get                    Use GET request method.\n" +
                "  --head                   Use HEAD request method.\n" +
                "  --options                Use OPTIONS request method.\n" +
                "  --trace                  Use TRACE request method.\n" +
                "  -?|-h|--help             This information.\n" +
                "  -V|--version             Display program version.\n");
    }

    public static void main(String[] args) {
        int option = 0;
        int optionsIndex = 0;
        char[] tmp;

        if (args.length == 0) {
            usage();
            System.exit(2);
        }

        for (String arg : args) {
            switch (arg) {
                case "0":
                    break;
                case "f":
                    force = 1;
                    break;
                case "r":
                    forceReload = 1;
                    break;
                case "9":
                    http10 = 0;
                    break;
                case "1":
                    http10 = 1;
                    break;
                case "2":
                    http10 = 2;
                    break;
                case "V":
                    System.out.println("PROGRAM_VERSION");
                    System.exit(0);
                case "t":
                    benchtime = Integer.parseInt(arg);
                    break;
                default:
                    break;
            }
        }
    }
}