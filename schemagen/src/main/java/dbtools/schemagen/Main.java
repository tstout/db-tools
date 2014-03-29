//package dbtools.schemagen;
//
//import com.google.common.base.Joiner;
//import dbtools.cmdopts.CmdEnv;
//import dbtools.cmdopts.CmdRegistry;
//import dbtools.cmdopts.ToolOptionParser;
//
//public class Main {
//    public static void main(String[] args) {
//        if (args.length == 0) {
//            new Commands(new CmdEnv()).showHelp(null);
//        }
//
//        CmdEnv env = new CmdEnv();
//
//        new CmdRegistry(new Commands(env))
//                .process(new ToolOptionParser()
//                        .parse(Joiner.on(' ').join(args)));
//    }
//}
//
//
