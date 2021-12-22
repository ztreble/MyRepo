package treblez.jvav.instruction.factory;

import treblez.jvav.instruction.base.Instruction;
import treblez.jvav.instruction.comparisons.*;
import treblez.jvav.instruction.constants.Const;
import treblez.jvav.instruction.constants.IPush;
import treblez.jvav.instruction.constants.Nop;
import treblez.jvav.instruction.control.Goto;
import treblez.jvav.instruction.control.LookUpSwitch;
import treblez.jvav.instruction.control.TableSwitch;
import treblez.jvav.instruction.conversions.D2x;
import treblez.jvav.instruction.conversions.F2x;
import treblez.jvav.instruction.conversions.I2x;
import treblez.jvav.instruction.conversions.L2x;
import treblez.jvav.instruction.extended.GoToW;
import treblez.jvav.instruction.extended.IfNull;
import treblez.jvav.instruction.extended.Wide;
import treblez.jvav.instruction.loads.*;
import treblez.jvav.instruction.math.*;
import treblez.jvav.instruction.stack.Dup;
import treblez.jvav.instruction.stack.Pop;
import treblez.jvav.instruction.stack.Swap;
import treblez.jvav.instruction.stores.*;

/**
 * @author treblez
 */
public class InstructionFactory {
    private static Instruction nop;
    private static Instruction aconst_null;
    private static Instruction iconst_m1;
    private static Instruction iconst_0;
    private static Instruction iconst_1;
    private static Instruction iconst_2;
    private static Instruction iconst_3;
    private static Instruction iconst_4;
    private static Instruction iconst_5;
    private static Instruction lconst_0;
    private static Instruction lconst_1;
    private static Instruction fconst_0;
    private static Instruction fconst_1;
    private static Instruction fconst_2;
    private static Instruction dconst_0;
    private static Instruction dconst_1;
    private static Instruction iload_0;
    private static Instruction iload_1;
    private static Instruction iload_2;
    private static Instruction iload_3;
    private static Instruction lload_0;
    private static Instruction lload_1;
    private static Instruction lload_2;
    private static Instruction lload_3;
    private static Instruction fload_0;
    private static Instruction fload_1;
    private static Instruction fload_2;
    private static Instruction fload_3;
    private static Instruction dload_0;
    private static Instruction dload_1;
    private static Instruction dload_2;
    private static Instruction dload_3;
    private static Instruction aload_0;
    private static Instruction aload_1;
    private static Instruction aload_2;
    private static Instruction aload_3;
    // iaload   {}
    // laload   {}
    // faload   {}
    // daload   {}
    // aaload   {}
    // baload   {}
    // caload   {}
    // saload   {}
    private static Instruction istore_0;
    private static Instruction istore_1;
    private static Instruction istore_2;
    private static Instruction istore_3;
    private static Instruction lstore_0;
    private static Instruction lstore_1;
    private static Instruction lstore_2;
    private static Instruction lstore_3;
    private static Instruction fstore_0;
    private static Instruction fstore_1;
    private static Instruction fstore_2;
    private static Instruction fstore_3;
    private static Instruction dstore_0;
    private static Instruction dstore_1;
    private static Instruction dstore_2;
    private static Instruction dstore_3;
    private static Instruction astore_0;
    private static Instruction astore_1;
    private static Instruction astore_2;
    private static Instruction astore_3;
    // iastore
    // lastore
    // fastore
    // dastore
    // aastore
    // bastore
    // castore
    // sastore
    private static Instruction pop     ;
    private static Instruction pop2    ;
    private static Instruction dup     ;
    private static Instruction dup_x1  ;
    private static Instruction dup_x2  ;
    private static Instruction dup2    ;
    private static Instruction dup2_x1 ;
    private static Instruction dup2_x2 ;
    private static Instruction swap    ;
    private static Instruction iadd    ;
    private static Instruction ladd    ;
    private static Instruction fadd    ;
    private static Instruction dadd    ;
    private static Instruction isub    ;
    private static Instruction lsub    ;
    private static Instruction fsub    ;
    private static Instruction dsub    ;
    private static Instruction imul    ;
    private static Instruction lmul    ;
    private static Instruction fmul    ;
    private static Instruction dmul    ;
    private static Instruction idiv    ;
    private static Instruction ldiv    ;
    private static Instruction fdiv    ;
    private static Instruction ddiv    ;
    private static Instruction irem    ;
    private static Instruction lrem    ;
    private static Instruction frem    ;
    private static Instruction drem    ;
    private static Instruction ineg    ;
    private static Instruction lneg    ;
    private static Instruction fneg    ;
    private static Instruction dneg    ;
    private static Instruction ishl    ;
    private static Instruction lshl    ;
    private static Instruction ishr    ;
    private static Instruction lshr    ;
    private static Instruction iushr   ;
    private static Instruction lushr   ;
    private static Instruction iand    ;
    private static Instruction land    ;
    private static Instruction ior     ;
    private static Instruction lor     ;
    private static Instruction ixor    ;
    private static Instruction lxor    ;
    private static Instruction i2l     ;
    private static Instruction i2f     ;
    private static Instruction i2d     ;
    private static Instruction l2i     ;
    private static Instruction l2f     ;
    private static Instruction l2d     ;
    private static Instruction f2i     ;
    private static Instruction f2l     ;
    private static Instruction f2d     ;
    private static Instruction d2i     ;
    private static Instruction d2l     ;
    private static Instruction d2f     ;
    private static Instruction i2b     ;
    private static Instruction i2c     ;
    private static Instruction i2s     ;
    private static Instruction lcmp    ;
    private static Instruction fcmpl   ;
    private static Instruction fcmpg   ;
    private static Instruction dcmpl   ;
    private static Instruction dcmpg   ;

    static{
        nop = new Nop();
        var _const = new Const();
        aconst_null = _const.new ACONST_NULL();
        iconst_m1   = _const.new ICONST_M1();
        iconst_0    = _const.new ICONST_0();
        iconst_1    = _const.new ICONST_1();
        iconst_2    = _const.new ICONST_2();
        iconst_3    = _const.new ICONST_3();
        iconst_4    = _const.new ICONST_4();
        iconst_5    = _const.new ICONST_5();
        lconst_0    = _const.new LCONST_0();
        lconst_1    = _const.new LCONST_1();
        fconst_0    = _const.new FCONST_0();
        fconst_1    = _const.new FCONST_1();
        fconst_2    = _const.new FCONST_2();
        dconst_0    = _const.new DCONST_0();
        dconst_1    = _const.new DCONST_1();
        var _iload = new Iload();
        iload_0     = _iload.new ILOAD_0();
        iload_1     = _iload.new ILOAD_1();
        iload_2     = _iload.new ILOAD_2();
        iload_3     = _iload.new ILOAD_3();
        var _lload = new Lload();
        lload_0     = _lload.new LLOAD_0();
        lload_1     = _lload.new LLOAD_1();
        lload_2     = _lload.new LLOAD_2();
        lload_3     = _lload.new LLOAD_3();
        var _fload = new Fload();
        fload_0     = _fload.new FLOAD_0();
        fload_1     = _fload.new FLOAD_1();
        fload_2     = _fload.new FLOAD_2();
        fload_3     = _fload.new FLOAD_3();
        var _dload = new Dload();
        dload_0     = _dload.new DLOAD_0();
        dload_1     = _dload.new DLOAD_1();
        dload_2     = _dload.new DLOAD_2();
        dload_3     = _dload.new DLOAD_3();
        var _aload = new Aload();
        aload_0     = _aload.new ALOAD_0();
        aload_1     = _aload.new ALOAD_1();
        aload_2     = _aload.new ALOAD_2();
        aload_3     = _aload.new ALOAD_3();
        // iaload      = &IALOAD{}
        // laload      = &LALOAD{}
        // faload      = &FALOAD{}
        // daload      = &DALOAD{}
        // aaload      = &AALOAD{}
        // baload      = &BALOAD{}
        // caload      = &CALOAD{}
        // saload      = &SALOAD{}
        var _istore = new Istore();
        istore_0 = _istore.new ISTORE_0();
        istore_1 = _istore.new ISTORE_1();
        istore_2 = _istore.new ISTORE_2();
        istore_3 = _istore.new ISTORE_3();
        var _lstore = new Lstore();
        lstore_0 = _lstore.new LSTORE_0();
        lstore_1 = _lstore.new LSTORE_1();
        lstore_2 = _lstore.new LSTORE_2();
        lstore_3 = _lstore.new LSTORE_3();
        var _fstore = new Fstore();
        fstore_0 = _fstore.new FSTORE_0();
        fstore_1 = _fstore.new FSTORE_1();
        fstore_2 = _fstore.new FSTORE_2();
        fstore_3 = _fstore.new FSTORE_3();
        var _dstore = new Dstore();
        dstore_0 = _dstore.new DSTORE_0();
        dstore_1 = _dstore.new DSTORE_1();
        dstore_2 = _dstore.new DSTORE_2();
        dstore_3 = _dstore.new DSTORE_3();
        var _astore = new Astore();
        astore_0 = _astore.new ASTORE_0();
        astore_1 = _astore.new ASTORE_1();
        astore_2 = _astore.new ASTORE_2();
        astore_3 = _astore.new ASTORE_3();
        // iastore  = &IASTORE{}
        // lastore  = &LASTORE{}
        // fastore  = &FASTORE{}
        // dastore  = &DASTORE{}
        // aastore  = &AASTORE{}
        // bastore  = &BASTORE{}
        // castore  = &CASTORE{}
        // sastore  = &SASTORE{}
        var _pop = new Pop();
        pop     = _pop.new POP();
        pop2    = _pop.new POP2();
        var _dup = new Dup();
        dup     = _dup.new DUP();
        dup_x1  = _dup.new DUP_X1();
        dup_x2  = _dup.new DUP_X2();
        dup2    = _dup.new DUP2();
        dup2_x1 = _dup.new DUP2_X1();
        dup2_x2 = _dup.new DUP2_X2();
        swap    = new Swap().new SWAP();
        var _add = new Add();
        iadd    = _add.new IADD();
        ladd    = _add.new LADD();
        fadd    = _add.new FADD();
        dadd    = _add.new DADD();
        var _sub = new Sub();
        isub    = _sub.new ISUB();
        lsub    = _sub.new LSUB();
        fsub    = _sub.new FSUB();
        dsub    = _sub.new DSUB();
        var _mul = new Mul();
        imul    = _mul.new IMUL();
        lmul    = _mul.new LMUL();
        fmul    = _mul.new FMUL();
        dmul    = _mul.new DMUL();
        var _div = new Div();
        idiv    = _div.new IDIV();
        ldiv    = _div.new LDIV();
        fdiv    = _div.new FDIV();
        ddiv    = _div.new DDIV();
        var _rem = new Rem();
        irem    = _rem.new IREM();
        lrem    = _rem.new LREM();
        frem    = _rem.new FREM();
        drem    = _rem.new DREM();
        var _neg = new Neg();
        ineg    = _neg.new INEG();
        lneg    = _neg.new LNEG();
        fneg    = _neg.new FNEG();
        dneg    = _neg.new DNEG();
        var _shl = new Sh();
        ishl    = _shl.new ISHL();
        lshl    = _shl.new LSHL();
        ishr    = _shl.new ISHR();
        lshr    = _shl.new LSHR();
        iushr   = _shl.new IUSHR();
        lushr   = _shl.new LUSHR();
        var _and = new And();
        iand    = _and.new IAND();
        land    = _and.new LAND();
        var _or = new Or();
        ior     = _or.new IOR();
        lor     = _or.new LOR();
        var _xor = new Xor();
        ixor    = _xor.new IXOR();
        lxor    = _xor.new LXOR();
        var _i2x = new I2x();
        i2l     = _i2x.new I2L();
        i2f     = _i2x.new I2F();
        i2d     = _i2x.new I2D();
        var _l2x = new L2x();
        l2i     = _l2x.new L2I();
        l2f     = _l2x.new L2F();
        l2d     = _l2x.new L2D();
        var _f2x = new F2x();
        f2i     = _f2x.new F2I();
        f2l     = _f2x.new F2L();
        f2d     = _f2x.new F2D();
        var _d2x = new D2x();
        d2i     = _d2x.new D2I();
        d2l     = _d2x.new D2L();
        d2f     = _d2x.new D2F();
        //var _i2x = new I2x();
        i2b     = _i2x.new I2B();
        i2c     = _i2x.new I2C();
        i2s     = _i2x.new I2S();
        var _lcmp = new Lcmp();
        lcmp    = _lcmp.new LCMP();
        var _fcmp = new Fcmp();
        fcmpl   = _fcmp.new FCMPL();
        fcmpg   = _fcmp.new FCMPG();
        var _dcmp = new Dcmp();
        dcmpl   = _dcmp.new DCMPL();
        dcmpg   = _dcmp.new DCMPG();
        // ireturn = &IRETURN{}
        // lreturn = &LRETURN{}
        // freturn = &FRETURN{}
        // dreturn = &DRETURN{}
        // areturn = &ARETURN{}
        // _return = &RETURN{}
        // arraylength   = &ARRAY_LENGTH{}
        // athrow        = &ATHROW{}
        // monitorenter  = &MONITOR_ENTER{}
        // monitorexit   = &MONITOR_EXIT{}
        // invoke_native = &INVOKE_NATIVE{}
    }

    public static Instruction newInstruction(byte opcode) throws Exception {
        switch (opcode) {
            case 0x00:
                return nop;
            case 0x01:
                return aconst_null;
            case 0x02:
                return iconst_m1;
            case 0x03:
                return iconst_0;
            case 0x04:
                return iconst_1;
            case 0x05:
                return iconst_2;
            case 0x06:
                return iconst_3;
            case 0x07:
                return iconst_4;
            case 0x08:
                return iconst_5;
            case 0x09:
                return lconst_0;
            case 0x0a:
                return lconst_1;
            case 0x0b:
                return fconst_0;
            case 0x0c:
                return fconst_1;
            case 0x0d:
                return fconst_2;
            case 0x0e:
                return dconst_0;
            case 0x0f:
                return dconst_1;
            case 0x10:
                return new IPush().new BIPUSH();
            case 0x11:
                return new IPush().new SIPUSH();
            // case 0x12:
            // 	return &LDC{}
            // case 0x13:
            // 	return &LDC_W{}
            // case 0x14:
            // 	return &LDC2_W{}
            case 0x15:
                return new Iload().new ILOAD();
            case 0x16:
                return new Lload().new LLOAD();
            case 0x17:
                return new Fload().new FLOAD();
            case 0x18:
                return new Dload().new DLOAD();
            case 0x19:
                return new Aload().new ALOAD();
            case 0x1a:
                return iload_0;
            case 0x1b:
                return iload_1;
            case 0x1c:
                return iload_2;
            case 0x1d:
                return iload_3;
            case 0x1e:
                return lload_0;
            case 0x1f:
                return lload_1;
            case 0x20:
                return lload_2;
            case 0x21:
                return lload_3;
            case 0x22:
                return fload_0;
            case 0x23:
                return fload_1;
            case 0x24:
                return fload_2;
            case 0x25:
                return fload_3;
            case 0x26:
                return dload_0;
            case 0x27:
                return dload_1;
            case 0x28:
                return dload_2;
            case 0x29:
                return dload_3;
            case 0x2a:
                return aload_0;
            case 0x2b:
                return aload_1;
            case 0x2c:
                return aload_2;
            case 0x2d:
                return aload_3;
            // case 0x2e:
            // 	return iaload
            // case 0x2f:
            // 	return laload
            // case 0x30:
            // 	return faload
            // case 0x31:
            // 	return daload
            // case 0x32:
            // 	return aaload
            // case 0x33:
            // 	return baload
            // case 0x34:
            // 	return caload
            // case 0x35:
            // 	return saload
            case 0x36:
                return new Istore().new ISTORE();
            case 0x37:
                return new Lstore().new LSTORE();
            case 0x38:
                return new Fstore().new FSTORE();
            case 0x39:
                return new Dstore().new DSTORE();
            case 0x3a:
                return new Astore().new ASTORE();
            case 0x3b:
                return istore_0;
            case 0x3c:
                return istore_1;
            case 0x3d:
                return istore_2;
            case 0x3e:
                return istore_3;
            case 0x3f:
                return lstore_0;
            case 0x40:
                return lstore_1;
            case 0x41:
                return lstore_2;
            case 0x42:
                return lstore_3;
            case 0x43:
                return fstore_0;
            case 0x44:
                return fstore_1;
            case 0x45:
                return fstore_2;
            case 0x46:
                return fstore_3;
            case 0x47:
                return dstore_0;
            case 0x48:
                return dstore_1;
            case 0x49:
                return dstore_2;
            case 0x4a:
                return dstore_3;
            case 0x4b:
                return astore_0;
            case 0x4c:
                return astore_1;
            case 0x4d:
                return astore_2;
            case 0x4e:
                return astore_3;
            // case 0x4f:
            // 	return iastore
            // case 0x50:
            // 	return lastore
            // case 0x51:
            // 	return fastore
            // case 0x52:
            // 	return dastore
            // case 0x53:
            // 	return aastore
            // case 0x54:
            // 	return bastore
            // case 0x55:
            // 	return castore
            // case 0x56:
            // 	return sastore
            case 0x57:
                return pop;
            case 0x58:
                return pop2;
            case 0x59:
                return dup;
            case 0x5a:
                return dup_x1;
            case 0x5b:
                return dup_x2;
            case 0x5c:
                return dup2;
            case 0x5d:
                return dup2_x1;
            case 0x5e:
                return dup2_x2;
            case 0x5f:
                return swap;
            case 0x60:
                return iadd;
            case 0x61:
                return ladd;
            case 0x62:
                return fadd;
            case 0x63:
                return dadd;
            case 0x64:
                return isub;
            case 0x65:
                return lsub;
            case 0x66:
                return fsub;
            case 0x67:
                return dsub;
            case 0x68:
                return imul;
            case 0x69:
                return lmul;
            case 0x6a:
                return fmul;
            case 0x6b:
                return dmul;
            case 0x6c:
                return idiv;
            case 0x6d:
                return ldiv;
            case 0x6e:
                return fdiv;
            case 0x6f:
                return ddiv;
            case 0x70:
                return irem;
            case 0x71:
                return lrem;
            case 0x72:
                return frem;
            case 0x73:
                return drem;
            case 0x74:
                return ineg;
            case 0x75:
                return lneg;
            case 0x76:
                return fneg;
            case 0x77:
                return dneg;
            case 0x78:
                return ishl;
            case 0x79:
                return lshl;
            case 0x7a:
                return ishr;
            case 0x7b:
                return lshr;
            case 0x7c:
                return iushr;
            case 0x7d:
                return lushr;
            case 0x7e:
                return iand;
            case 0x7f:
                return land;
            case 0x80:
                return ior;
            case 0x81:
                return lor;
            case 0x82:
                return ixor;
            case 0x83:
                return lxor;
            case 0x84:
                return new Iinc().new IINC();
            case 0x85:
                return i2l;
            case 0x86:
                return i2f;
            case 0x87:
                return i2d;
            case 0x88:
                return l2i;
            case 0x89:
                return l2f;
            case 0x8a:
                return l2d;
            case 0x8b:
                return f2i;
            case 0x8c:
                return f2l;
            case 0x8d:
                return f2d;
            case 0x8e:
                return d2i;
            case 0x8f:
                return d2l;
            case 0x90:
                return d2f;
            case 0x91:
                return i2b;
            case 0x92:
                return i2c;
            case 0x93:
                return i2s;
            case 0x94:
                return lcmp;
            case 0x95:
                return fcmpl;
            case 0x96:
                return fcmpg;
            case 0x97:
                return dcmpl;
            case 0x98:
                return dcmpg;
            case 0x99:
                return new Ifcond().new IFEQ();
            case 0x9a:
                return new Ifcond().new IFNE();
            case 0x9b:
                return new Ifcond().new IFLT();
            case 0x9c:
                return new Ifcond().new IFGE();
            case 0x9d:
                return new Ifcond().new IFGT();
            case 0x9e:
                return new Ifcond().new IFLE();
            case 0x9f:
                return new Ificmp().new IF_ICMPEQ();
            case 0xa0:
                return new Ificmp().new IF_ICMPNE();
            case 0xa1:
                return new Ificmp().new IF_ICMPLT();
            case 0xa2:
                return new Ificmp().new IF_ICMPGE();
            case 0xa3:
                return new Ificmp().new IF_ICMPGT();
            case 0xa4:
                return new Ificmp().new IF_ICMPLE();
            case 0xa5:
                return new Ifacmp().new IF_ACMPEQ();
            case 0xa6:
                return new Ifacmp().new IF_ACMPNE();
            case 0xa7:
                return new Goto();
            // case 0xa8:
            // 	return &JSR{}
            // case 0xa9:
            // 	return &RET{}
            case 0xaa:
                return new TableSwitch();
            case 0xab:
                return new LookUpSwitch();
            // case 0xac:
            // 	return ireturn
            // case 0xad:
            // 	return lreturn
            // case 0xae:
            // 	return freturn
            // case 0xaf:
            // 	return dreturn
            // case 0xb0:
            // 	return areturn
            // case 0xb1:
            // 	return _return
            //	case 0xb2:
            //		return &GET_STATIC{}
            // case 0xb3:
            // 	return &PUT_STATIC{}
            // case 0xb4:
            // 	return &GET_FIELD{}
            // case 0xb5:
            // 	return &PUT_FIELD{}
            //	case 0xb6:
            //		return &INVOKE_VIRTUAL{}
            // case 0xb7:
            // 	return &INVOKE_SPECIAL{}
            // case 0xb8:
            // 	return &INVOKE_STATIC{}
            // case 0xb9:
            // 	return &INVOKE_INTERFACE{}
            // case 0xba:
            // 	return &INVOKE_DYNAMIC{}
            // case 0xbb:
            // 	return &NEW{}
            // case 0xbc:
            // 	return &NEW_ARRAY{}
            // case 0xbd:
            // 	return &ANEW_ARRAY{}
            // case 0xbe:
            // 	return arraylength
            // case 0xbf:
            // 	return athrow
            // case 0xc0:
            // 	return &CHECK_CAST{}
            // case 0xc1:
            // 	return &INSTANCE_OF{}
            // case 0xc2:
            // 	return monitorenter
            // case 0xc3:
            // 	return monitorexit
            case 0xc4:
                return new Wide();
            // case 0xc5:
            // 	return &MULTI_ANEW_ARRAY{}
            case 0xc6:
                return new IfNull().new IFNULL();
            case 0xc7:
                return new IfNull().new IFNONNULL();
            case 0xc8:
                return new GoToW();
            // case 0xc9:
            // 	return &JSR_W{}
            // case 0xca: breakpoint
            // case 0xfe: impdep1
            // case 0xff: impdep2
            default:
                throw new Exception("Unsupported opcode");
        }
    }
}
