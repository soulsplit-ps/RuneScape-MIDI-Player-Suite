package main;

public class RawPcmStream extends PcmStream {

    int attackEnvelopeFactor;
    int notePitchFactor;
    int noteVelocityFactor;
    int __o;
    int __u;
    int rightChannelPan;
    int leftChannelPan;
    int loopValue;
    int start;
    int end;
    boolean enableEffects;
    int surfaceOffsetY;
    int __i;
    int __a;
    int __z;

    RawPcmStream(AudioBuffer var1, int var2, int var3, int var4) {
        super.sound = var1;
        this.start = var1.start;
        this.end = var1.end;
        this.enableEffects = var1.bool;
        this.notePitchFactor = var2;
        this.noteVelocityFactor = var3;
        this.__o = var4;
        this.attackEnvelopeFactor = 0;
        this.__o_180();
    }

    RawPcmStream(AudioBuffer var1, int var2, int var3) {
        super.sound = var1;
        this.start = var1.start;
        this.end = var1.end;
        this.enableEffects = var1.bool;
        this.notePitchFactor = var2;
        this.noteVelocityFactor = var3;
        this.__o = 8192;
        this.attackEnvelopeFactor = 0;
        this.__o_180();
    }

    void __o_180() {
        this.__u = this.noteVelocityFactor;
        this.rightChannelPan = method2603(this.noteVelocityFactor, this.__o);
        this.leftChannelPan = method2494(this.noteVelocityFactor, this.__o);
    }

    protected PcmStream firstSubStream() {
        return null;
    }

    protected PcmStream nextSubStream() {
        return null;
    }

    protected int __l_171() {
        return this.noteVelocityFactor == 0 && this.surfaceOffsetY == 0?0:1;
    }

    public synchronized void __e_172(int[] var1, int var2, int var3) {
        if(this.noteVelocityFactor == 0 && this.surfaceOffsetY == 0) {
            this.__d_173(var3);
        } else {
            AudioBuffer var4 = (AudioBuffer)super.sound;
            int var5 = this.start << 8;
            int var6 = this.end << 8;
            int var7 = var4.samples.length << 8;
            int var8 = var6 - var5;
            if(var8 <= 0) {
                this.loopValue = 0;
            }

            int var9 = var2;
            var3 += var2;
            if(this.attackEnvelopeFactor < 0) {
                if(this.notePitchFactor <= 0) {
                    this.__b_189();
                    this.remove();
                    return;
                }

                this.attackEnvelopeFactor = 0;
            }

            if(this.attackEnvelopeFactor >= var7) {
                if(this.notePitchFactor >= 0) {
                    this.__b_189();
                    this.remove();
                    return;
                }

                this.attackEnvelopeFactor = var7 - 1;
            }

            if(this.loopValue < 0) {
                if(this.enableEffects) {
                    if(this.notePitchFactor < 0) {
                        var9 = this.__ap_203(var1, var2, var5, var3, var4.samples[this.start]);
                        if(this.attackEnvelopeFactor >= var5) {
                            return;
                        }

                        this.attackEnvelopeFactor = var5 + var5 - 1 - this.attackEnvelopeFactor;
                        this.notePitchFactor = -this.notePitchFactor;
                    }

                    while(true) {
                        var9 = this.__ad_202(var1, var9, var6, var3, var4.samples[this.end - 1]);
                        if(this.attackEnvelopeFactor < var6) {
                            return;
                        }

                        this.attackEnvelopeFactor = var6 + var6 - 1 - this.attackEnvelopeFactor;
                        this.notePitchFactor = -this.notePitchFactor;
                        var9 = this.__ap_203(var1, var9, var5, var3, var4.samples[this.start]);
                        if(this.attackEnvelopeFactor >= var5) {
                            return;
                        }

                        this.attackEnvelopeFactor = var5 + var5 - 1 - this.attackEnvelopeFactor;
                        this.notePitchFactor = -this.notePitchFactor;
                    }
                }

                if(this.notePitchFactor < 0) {
                    while(true) {
                        var9 = this.__ap_203(var1, var9, var5, var3, var4.samples[this.end - 1]);
                        if(this.attackEnvelopeFactor >= var5) {
                            return;
                        }

                        this.attackEnvelopeFactor = var6 - 1 - (var6 - 1 - this.attackEnvelopeFactor) % var8;
                    }
                }

                while(true) {
                    var9 = this.__ad_202(var1, var9, var6, var3, var4.samples[this.start]);
                    if(this.attackEnvelopeFactor < var6) {
                        return;
                    }

                    this.attackEnvelopeFactor = var5 + (this.attackEnvelopeFactor - var5) % var8;
                }
            }

            if(this.loopValue > 0) {
                if(this.enableEffects) {
                    label118: {
                        if(this.notePitchFactor < 0) {
                            var9 = this.__ap_203(var1, var2, var5, var3, var4.samples[this.start]);
                            if(this.attackEnvelopeFactor >= var5) {
                                return;
                            }

                            this.attackEnvelopeFactor = var5 + var5 - 1 - this.attackEnvelopeFactor;
                            this.notePitchFactor = -this.notePitchFactor;
                            if(--this.loopValue == 0) {
                                break label118;
                            }
                        }

                        do {
                            var9 = this.__ad_202(var1, var9, var6, var3, var4.samples[this.end - 1]);
                            if(this.attackEnvelopeFactor < var6) {
                                return;
                            }

                            this.attackEnvelopeFactor = var6 + var6 - 1 - this.attackEnvelopeFactor;
                            this.notePitchFactor = -this.notePitchFactor;
                            if(--this.loopValue == 0) {
                                break;
                            }

                            var9 = this.__ap_203(var1, var9, var5, var3, var4.samples[this.start]);
                            if(this.attackEnvelopeFactor >= var5) {
                                return;
                            }

                            this.attackEnvelopeFactor = var5 + var5 - 1 - this.attackEnvelopeFactor;
                            this.notePitchFactor = -this.notePitchFactor;
                        } while(--this.loopValue != 0);
                    }
                } else {
                    int var10;
                    if(this.notePitchFactor < 0) {
                        while(true) {
                            var9 = this.__ap_203(var1, var9, var5, var3, var4.samples[this.end - 1]);
                            if(this.attackEnvelopeFactor >= var5) {
                                return;
                            }

                            var10 = (var6 - 1 - this.attackEnvelopeFactor) / var8;
                            if(var10 >= this.loopValue) {
                                this.attackEnvelopeFactor += var8 * this.loopValue;
                                this.loopValue = 0;
                                break;
                            }

                            this.attackEnvelopeFactor += var8 * var10;
                            this.loopValue -= var10;
                        }
                    } else {
                        while(true) {
                            var9 = this.__ad_202(var1, var9, var6, var3, var4.samples[this.start]);
                            if(this.attackEnvelopeFactor < var6) {
                                return;
                            }

                            var10 = (this.attackEnvelopeFactor - var5) / var8;
                            if(var10 >= this.loopValue) {
                                this.attackEnvelopeFactor -= var8 * this.loopValue;
                                this.loopValue = 0;
                                break;
                            }

                            this.attackEnvelopeFactor -= var8 * var10;
                            this.loopValue -= var10;
                        }
                    }
                }
            }

            if(this.notePitchFactor < 0) {
                this.__ap_203(var1, var9, 0, var3, 0);
                if(this.attackEnvelopeFactor < 0) {
                    this.attackEnvelopeFactor = -1;
                    this.__b_189();
                    this.remove();
                }
            } else {
                this.__ad_202(var1, var9, var7, var3, 0);
                if(this.attackEnvelopeFactor >= var7) {
                    this.attackEnvelopeFactor = var7;
                    this.__b_189();
                    this.remove();
                }
            }
        }

    }

    public synchronized void setLoopOnSample(int loopMode) {
        this.loopValue = loopMode;
    }

    public synchronized void __d_173(int var1) {
        if(this.surfaceOffsetY > 0) {
            if(var1 >= this.surfaceOffsetY) {
                if(this.noteVelocityFactor == Integer.MIN_VALUE) {
                    this.noteVelocityFactor = 0;
                    this.leftChannelPan = 0;
                    this.rightChannelPan = 0;
                    this.__u = 0;
                    this.remove();
                    var1 = this.surfaceOffsetY;
                }

                this.surfaceOffsetY = 0;
                this.__o_180();
            } else {
                this.__u += this.__i * var1;
                this.rightChannelPan += this.__a * var1;
                this.leftChannelPan += this.__z * var1;
                this.surfaceOffsetY -= var1;
            }
        }

        AudioBuffer var2 = (AudioBuffer)super.sound;
        int var3 = this.start << 8;
        int var4 = this.end << 8;
        int var5 = var2.samples.length << 8;
        int var6 = var4 - var3;
        if(var6 <= 0) {
            this.loopValue = 0;
        }

        if(this.attackEnvelopeFactor < 0) {
            if(this.notePitchFactor <= 0) {
                this.__b_189();
                this.remove();
                return;
            }

            this.attackEnvelopeFactor = 0;
        }

        if(this.attackEnvelopeFactor >= var5) {
            if(this.notePitchFactor >= 0) {
                this.__b_189();
                this.remove();
                return;
            }

            this.attackEnvelopeFactor = var5 - 1;
        }

        this.attackEnvelopeFactor += this.notePitchFactor * var1;
        if(this.loopValue < 0) {
            if(!this.enableEffects) {
                if(this.notePitchFactor < 0) {
                    if(this.attackEnvelopeFactor >= var3) {
                        return;
                    }

                    this.attackEnvelopeFactor = var4 - 1 - (var4 - 1 - this.attackEnvelopeFactor) % var6;
                } else {
                    if(this.attackEnvelopeFactor < var4) {
                        return;
                    }

                    this.attackEnvelopeFactor = var3 + (this.attackEnvelopeFactor - var3) % var6;
                }
            } else {
                if(this.notePitchFactor < 0) {
                    if(this.attackEnvelopeFactor >= var3) {
                        return;
                    }

                    this.attackEnvelopeFactor = var3 + var3 - 1 - this.attackEnvelopeFactor;
                    this.notePitchFactor = -this.notePitchFactor;
                }

                while(this.attackEnvelopeFactor >= var4) {
                    this.attackEnvelopeFactor = var4 + var4 - 1 - this.attackEnvelopeFactor;
                    this.notePitchFactor = -this.notePitchFactor;
                    if(this.attackEnvelopeFactor >= var3) {
                        return;
                    }

                    this.attackEnvelopeFactor = var3 + var3 - 1 - this.attackEnvelopeFactor;
                    this.notePitchFactor = -this.notePitchFactor;
                }
            }
        } else {
            if(this.loopValue > 0) {
                if(this.enableEffects) {
                    label113: {
                        if(this.notePitchFactor < 0) {
                            if(this.attackEnvelopeFactor >= var3) {
                                return;
                            }

                            this.attackEnvelopeFactor = var3 + var3 - 1 - this.attackEnvelopeFactor;
                            this.notePitchFactor = -this.notePitchFactor;
                            if(--this.loopValue == 0) {
                                break label113;
                            }
                        }

                        do {
                            if(this.attackEnvelopeFactor < var4) {
                                return;
                            }

                            this.attackEnvelopeFactor = var4 + var4 - 1 - this.attackEnvelopeFactor;
                            this.notePitchFactor = -this.notePitchFactor;
                            if(--this.loopValue == 0) {
                                break;
                            }

                            if(this.attackEnvelopeFactor >= var3) {
                                return;
                            }

                            this.attackEnvelopeFactor = var3 + var3 - 1 - this.attackEnvelopeFactor;
                            this.notePitchFactor = -this.notePitchFactor;
                        } while(--this.loopValue != 0);
                    }
                } else {
                    int var7;
                    if(this.notePitchFactor < 0) {
                        if(this.attackEnvelopeFactor >= var3) {
                            return;
                        }

                        var7 = (var4 - 1 - this.attackEnvelopeFactor) / var6;
                        if(var7 < this.loopValue) {
                            this.attackEnvelopeFactor += var6 * var7;
                            this.loopValue -= var7;
                            return;
                        }

                        this.attackEnvelopeFactor += var6 * this.loopValue;
                        this.loopValue = 0;
                    } else {
                        if(this.attackEnvelopeFactor < var4) {
                            return;
                        }

                        var7 = (this.attackEnvelopeFactor - var3) / var6;
                        if(var7 < this.loopValue) {
                            this.attackEnvelopeFactor -= var6 * var7;
                            this.loopValue -= var7;
                            return;
                        }

                        this.attackEnvelopeFactor -= var6 * this.loopValue;
                        this.loopValue = 0;
                    }
                }
            }

            if(this.notePitchFactor < 0) {
                if(this.attackEnvelopeFactor < 0) {
                    this.attackEnvelopeFactor = -1;
                    this.__b_189();
                    this.remove();
                }
            } else if(this.attackEnvelopeFactor >= var5) {
                this.attackEnvelopeFactor = var5;
                this.__b_189();
                this.remove();
            }
        }

    }

    public synchronized void __a_182(int var1) {
        this.__j_184(var1 << 6, this.__t_186());
    }

    synchronized void __z_183(int var1) {
        this.__j_184(var1, this.__t_186());
    }

    synchronized void __j_184(int var1, int var2) {
        this.noteVelocityFactor = var1;
        this.__o = var2;
        this.surfaceOffsetY = 0;
        this.__o_180();
    }

    public synchronized int __s_185() {
        return this.noteVelocityFactor == Integer.MIN_VALUE?0:this.noteVelocityFactor;
    }

    public synchronized int __t_186() {
        return this.__o < 0?-1:this.__o;
    }

    public synchronized void __y_187(int var1) {
        int var2 = ((AudioBuffer)super.sound).samples.length << 8;
        if(var1 < -1) {
            var1 = -1;
        }

        if(var1 > var2) {
            var1 = var2;
        }

        this.attackEnvelopeFactor = var1;
    }

    public synchronized void __h_188() {
        this.notePitchFactor = (this.notePitchFactor ^ this.notePitchFactor >> 31) + (this.notePitchFactor >>> 31);
        this.notePitchFactor = -this.notePitchFactor;
    }

    void __b_189() {
        if(this.surfaceOffsetY != 0) {
            if(this.noteVelocityFactor == Integer.MIN_VALUE) {
                this.noteVelocityFactor = 0;
            }

            this.surfaceOffsetY = 0;
            this.__o_180();
        }

    }

    public synchronized void __c_190(int var1, int var2) {
        this.__p_191(var1, var2, this.__t_186());
    }

    public synchronized void __p_191(int var1, int var2, int var3) {
        if(var1 == 0) {
            this.__j_184(var2, var3);
        } else {
            int var4 = method2603(var2, var3);
            int var5 = method2494(var2, var3);
            if(var4 == this.rightChannelPan && var5 == this.leftChannelPan) {
                this.surfaceOffsetY = 0;
            } else {
                int var6 = var2 - this.__u;
                if(this.__u - var2 > var6) {
                    var6 = this.__u - var2;
                }

                if(var4 - this.rightChannelPan > var6) {
                    var6 = var4 - this.rightChannelPan;
                }

                if(this.rightChannelPan - var4 > var6) {
                    var6 = this.rightChannelPan - var4;
                }

                if(var5 - this.leftChannelPan > var6) {
                    var6 = var5 - this.leftChannelPan;
                }

                if(this.leftChannelPan - var5 > var6) {
                    var6 = this.leftChannelPan - var5;
                }

                if(var1 > var6) {
                    var1 = var6;
                }

                this.surfaceOffsetY = var1;
                this.noteVelocityFactor = var2;
                this.__o = var3;
                this.__i = (var2 - this.__u) / var1;
                this.__a = (var4 - this.rightChannelPan) / var1;
                this.__z = (var5 - this.leftChannelPan) / var1;
            }
        }

    }

    public synchronized void __v_192(int var1) {
        if(var1 == 0) {
            this.__z_183(0);
            this.remove();
        } else if(this.rightChannelPan == 0 && this.leftChannelPan == 0) {
            this.surfaceOffsetY = 0;
            this.noteVelocityFactor = 0;
            this.__u = 0;
            this.remove();
        } else {
            int var2 = -this.__u;
            if(this.__u > var2) {
                var2 = this.__u;
            }

            if(-this.rightChannelPan > var2) {
                var2 = -this.rightChannelPan;
            }

            if(this.rightChannelPan > var2) {
                var2 = this.rightChannelPan;
            }

            if(-this.leftChannelPan > var2) {
                var2 = -this.leftChannelPan;
            }

            if(this.leftChannelPan > var2) {
                var2 = this.leftChannelPan;
            }

            if(var1 > var2) {
                var1 = var2;
            }

            this.surfaceOffsetY = var1;
            this.noteVelocityFactor = Integer.MIN_VALUE;
            this.__i = -this.__u / var1;
            this.__a = -this.rightChannelPan / var1;
            this.__z = -this.leftChannelPan / var1;
        }

    }

    public synchronized void __ah_193(int var1) {
        if(this.notePitchFactor < 0) {
            this.notePitchFactor = -var1;
        } else {
            this.notePitchFactor = var1;
        }

    }

    public synchronized int __ab_194() {
        return this.notePitchFactor < 0?-this.notePitchFactor:this.notePitchFactor;
    }

    public boolean __ae_195() {
        return this.attackEnvelopeFactor < 0 || this.attackEnvelopeFactor >= ((AudioBuffer)super.sound).samples.length << 8;
    }

    public boolean __at_196() {
        return this.surfaceOffsetY != 0;
    }

    int __ad_202(int[] var1, int var2, int var3, int var4, int var5) {
        while(true) {
            if(this.surfaceOffsetY > 0) {
                int var6 = var2 + this.surfaceOffsetY;
                if(var6 > var4) {
                    var6 = var4;
                }

                this.surfaceOffsetY += var2;
                if(this.notePitchFactor == 256 && (this.attackEnvelopeFactor & 255) == 0) {
                    if(AudioConstants.isStereo) {
                        var2 = method2533(0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, this.__a, this.__z, 0, var6, var3, this);
                    } else {
                        var2 = method2532(((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, this.__i, 0, var6, var3, this);
                    }
                } else if(AudioConstants.isStereo) {
                    var2 = method2504(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, this.__a, this.__z, 0, var6, var3, this, this.notePitchFactor, var5);
                } else {
                    var2 = method2536(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, this.__i, 0, var6, var3, this, this.notePitchFactor, var5);
                }

                this.surfaceOffsetY -= var2;
                if(this.surfaceOffsetY != 0) {
                    return var2;
                }

                if(!this.__au_204()) {
                    continue;
                }

                return var4;
            }

            if(this.notePitchFactor == 256 && (this.attackEnvelopeFactor & 255) == 0) {
                if(AudioConstants.isStereo) {
                    return method2525(0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, 0, var4, var3, this);
                }

                return method2563(((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, 0, var4, var3, this);
            }

            if(AudioConstants.isStereo) {
                return method2529(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, 0, var4, var3, this, this.notePitchFactor, var5);
            }

            return method2528(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, 0, var4, var3, this, this.notePitchFactor, var5);
        }
    }

    int __ap_203(int[] var1, int var2, int var3, int var4, int var5) {
        while(true) {
            if(this.surfaceOffsetY > 0) {
                int var6 = var2 + this.surfaceOffsetY;
                if(var6 > var4) {
                    var6 = var4;
                }

                this.surfaceOffsetY += var2;
                if(this.notePitchFactor == -256 && (this.attackEnvelopeFactor & 255) == 0) {
                    if(AudioConstants.isStereo) {
                        var2 = method2547(0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, this.__a, this.__z, 0, var6, var3, this);
                    } else {
                        var2 = method2534(((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, this.__i, 0, var6, var3, this);
                    }
                } else if(AudioConstants.isStereo) {
                    var2 = method2624(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, this.__a, this.__z, 0, var6, var3, this, this.notePitchFactor, var5);
                } else {
                    var2 = method2538(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, this.__i, 0, var6, var3, this, this.notePitchFactor, var5);
                }

                this.surfaceOffsetY -= var2;
                if(this.surfaceOffsetY != 0) {
                    return var2;
                }

                if(!this.__au_204()) {
                    continue;
                }

                return var4;
            }

            if(this.notePitchFactor == -256 && (this.attackEnvelopeFactor & 255) == 0) {
                if(AudioConstants.isStereo) {
                    return method2496(0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, 0, var4, var3, this);
                }

                return method2636(((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, 0, var4, var3, this);
            }

            if(AudioConstants.isStereo) {
                return method2531(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.rightChannelPan, this.leftChannelPan, 0, var4, var3, this, this.notePitchFactor, var5);
            }

            return method2509(0, 0, ((AudioBuffer)super.sound).samples, var1, this.attackEnvelopeFactor, var2, this.__u, 0, var4, var3, this, this.notePitchFactor, var5);
        }
    }

    int __az_179() {
        int var1 = this.__u * 3 >> 6;
        var1 = (var1 ^ var1 >> 31) + (var1 >>> 31);
        if(this.loopValue == 0) {
            var1 -= var1 * this.attackEnvelopeFactor / (((AudioBuffer)super.sound).samples.length << 8);
        } else if(this.loopValue >= 0) {
            var1 -= var1 * this.start / ((AudioBuffer)super.sound).samples.length;
        }

        return var1 > 255?255:var1;
    }

    boolean __au_204() {
        int var1 = this.noteVelocityFactor;
        int var2;
        int var3;
        if(var1 == Integer.MIN_VALUE) {
            var3 = 0;
            var2 = 0;
            var1 = 0;
        } else {
            var2 = method2603(var1, this.__o);
            var3 = method2494(var1, this.__o);
        }

        if(var1 == this.__u && var2 == this.rightChannelPan && var3 == this.leftChannelPan) {
            if(this.noteVelocityFactor == Integer.MIN_VALUE) {
                this.noteVelocityFactor = 0;
                this.leftChannelPan = 0;
                this.rightChannelPan = 0;
                this.__u = 0;
                this.remove();
                return true;
            } else {
                this.__o_180();
                return false;
            }
        } else {
            if(this.__u < var1) {
                this.__i = 1;
                this.surfaceOffsetY = var1 - this.__u;
            } else if(this.__u > var1) {
                this.__i = -1;
                this.surfaceOffsetY = this.__u - var1;
            } else {
                this.__i = 0;
            }

            if(this.rightChannelPan < var2) {
                this.__a = 1;
                if(this.surfaceOffsetY == 0 || this.surfaceOffsetY > var2 - this.rightChannelPan) {
                    this.surfaceOffsetY = var2 - this.rightChannelPan;
                }
            } else if(this.rightChannelPan > var2) {
                this.__a = -1;
                if(this.surfaceOffsetY == 0 || this.surfaceOffsetY > this.rightChannelPan - var2) {
                    this.surfaceOffsetY = this.rightChannelPan - var2;
                }
            } else {
                this.__a = 0;
            }

            if(this.leftChannelPan < var3) {
                this.__z = 1;
                if(this.surfaceOffsetY == 0 || this.surfaceOffsetY > var3 - this.leftChannelPan) {
                    this.surfaceOffsetY = var3 - this.leftChannelPan;
                }
            } else if(this.leftChannelPan > var3) {
                this.__z = -1;
                if(this.surfaceOffsetY == 0 || this.surfaceOffsetY > this.leftChannelPan - var3) {
                    this.surfaceOffsetY = this.leftChannelPan - var3;
                }
            } else {
                this.__z = 0;
            }

            return false;
        }
    }

    static int method2603(int var0, int var1) {
        return var1 < 0?var0:(int)((double)var0 * Math.sqrt((double)(16384 - var1) * 1.220703125E-4D) + 0.5D);
    }

    static int method2494(int var0, int var1) {
        return var1 < 0?-var0:(int)((double)var0 * Math.sqrt((double)var1 * 1.220703125E-4D) + 0.5D);
    }

    public static RawPcmStream method2497(AudioBuffer rawSample, int var1, int var2) {
        return rawSample.samples != null && rawSample.samples.length != 0?new RawPcmStream(rawSample, (int)((long)rawSample.sampleRate * 256L * (long)var1 / (long)(AudioConstants.systemSampleRate * 100)), var2 << 6):null;
    }

    public static RawPcmStream method2524(AudioBuffer rawSample, int samplePitch, int velocityLeftChannel, int velocityRightChannel) {
        return rawSample.samples != null && rawSample.samples.length != 0?new RawPcmStream(rawSample, samplePitch, velocityLeftChannel, velocityRightChannel):null;
    }

    static int method2563(byte[] var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, RawPcmStream var8) {
        var2 >>= 8;
        var7 >>= 8;
        var4 <<= 2;
        if((var5 = var3 + var7 - var2) > var6) {
            var5 = var6;
        }

        int var9;
        for(var5 -= 3; var3 < var5; var1[var9] += var0[var2++] * var4) {
            var9 = var3++;
            var1[var9] += var0[var2++] * var4;
            var9 = var3++;
            var1[var9] += var0[var2++] * var4;
            var9 = var3++;
            var1[var9] += var0[var2++] * var4;
            var9 = var3++;
        }

        for(var5 += 3; var3 < var5; var1[var9] += var0[var2++] * var4) {
            var9 = var3++;
        }

        var8.attackEnvelopeFactor = var2 << 8;
        return var3;
    }

    static int method2525(int var0, byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, RawPcmStream var10) {
        var3 >>= 8;
        var9 >>= 8;
        var5 <<= 2;
        var6 <<= 2;
        if((var7 = var4 + var9 - var3) > var8) {
            var7 = var8;
        }

        var4 <<= 1;
        var7 <<= 1;

        int var11;
        byte var12;
        for(var7 -= 6; var4 < var7; var2[var11] += var12 * var6) {
            var12 = var1[var3++];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
            var2[var11] += var12 * var6;
            var12 = var1[var3++];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
            var2[var11] += var12 * var6;
            var12 = var1[var3++];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
            var2[var11] += var12 * var6;
            var12 = var1[var3++];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
        }

        for(var7 += 6; var4 < var7; var2[var11] += var12 * var6) {
            var12 = var1[var3++];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
        }

        var10.attackEnvelopeFactor = var3 << 8;
        return var4 >> 1;
    }

    static int method2636(byte[] var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, RawPcmStream var8) {
        var2 >>= 8;
        var7 >>= 8;
        var4 <<= 2;
        if((var5 = var3 + var2 - (var7 - 1)) > var6) {
            var5 = var6;
        }

        int var9;
        for(var5 -= 3; var3 < var5; var1[var9] += var0[var2--] * var4) {
            var9 = var3++;
            var1[var9] += var0[var2--] * var4;
            var9 = var3++;
            var1[var9] += var0[var2--] * var4;
            var9 = var3++;
            var1[var9] += var0[var2--] * var4;
            var9 = var3++;
        }

        for(var5 += 3; var3 < var5; var1[var9] += var0[var2--] * var4) {
            var9 = var3++;
        }

        var8.attackEnvelopeFactor = var2 << 8;
        return var3;
    }

    static int method2496(int var0, byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, RawPcmStream var10) {
        var3 >>= 8;
        var9 >>= 8;
        var5 <<= 2;
        var6 <<= 2;
        if((var7 = var3 + var4 - (var9 - 1)) > var8) {
            var7 = var8;
        }

        var4 <<= 1;
        var7 <<= 1;

        int var11;
        byte var12;
        for(var7 -= 6; var4 < var7; var2[var11] += var12 * var6) {
            var12 = var1[var3--];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
            var2[var11] += var12 * var6;
            var12 = var1[var3--];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
            var2[var11] += var12 * var6;
            var12 = var1[var3--];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
            var2[var11] += var12 * var6;
            var12 = var1[var3--];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
        }

        for(var7 += 6; var4 < var7; var2[var11] += var12 * var6) {
            var12 = var1[var3--];
            var11 = var4++;
            var2[var11] += var12 * var5;
            var11 = var4++;
        }

        var10.attackEnvelopeFactor = var3 << 8;
        return var4 >> 1;
    }

    static int method2528(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, RawPcmStream var10, int var11, int var12) {
        if(var11 == 0 || (var7 = var5 + (var11 + (var9 - var4) - 257) / var11) > var8) {
            var7 = var8;
        }

        byte var13;
        int var14;
        while(var5 < var7) {
            var1 = var4 >> 8;
            var13 = var2[var1];
            var14 = var5++;
            var3[var14] += ((var13 << 8) + (var2[var1 + 1] - var13) * (var4 & 255)) * var6 >> 6;
            var4 += var11;
        }

        if(var11 == 0 || (var7 = var5 + (var11 + (var9 - var4) - 1) / var11) > var8) {
            var7 = var8;
        }

        for(var1 = var12; var5 < var7; var4 += var11) {
            var13 = var2[var4 >> 8];
            var14 = var5++;
            var3[var14] += ((var13 << 8) + (var1 - var13) * (var4 & 255)) * var6 >> 6;
        }

        var10.attackEnvelopeFactor = var4;
        return var5;
    }

    static int method2529(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, RawPcmStream var11, int var12, int var13) {
        if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12 - 257) / var12) > var9) {
            var8 = var9;
        }

        var5 <<= 1;

        byte var14;
        int var15;
        for(var8 <<= 1; var5 < var8; var4 += var12) {
            var1 = var4 >> 8;
            var14 = var2[var1];
            var0 = (var14 << 8) + (var4 & 255) * (var2[var1 + 1] - var14);
            var15 = var5++;
            var3[var15] += var0 * var6 >> 6;
            var15 = var5++;
            var3[var15] += var0 * var7 >> 6;
        }

        if(var12 == 0 || (var8 = (var5 >> 1) + (var10 - var4 + var12 - 1) / var12) > var9) {
            var8 = var9;
        }

        var8 <<= 1;

        for(var1 = var13; var5 < var8; var4 += var12) {
            var14 = var2[var4 >> 8];
            var0 = (var14 << 8) + (var1 - var14) * (var4 & 255);
            var15 = var5++;
            var3[var15] += var0 * var6 >> 6;
            var15 = var5++;
            var3[var15] += var0 * var7 >> 6;
        }

        var11.attackEnvelopeFactor = var4;
        return var5 >> 1;
    }

    static int method2509(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, RawPcmStream var10, int var11, int var12) {
        if(var11 == 0 || (var7 = var5 + (var11 + (var9 + 256 - var4)) / var11) > var8) {
            var7 = var8;
        }

        int var13;
        while(var5 < var7) {
            var1 = var4 >> 8;
            byte var14 = var2[var1 - 1];
            var13 = var5++;
            var3[var13] += ((var14 << 8) + (var2[var1] - var14) * (var4 & 255)) * var6 >> 6;
            var4 += var11;
        }

        if(var11 == 0 || (var7 = var5 + (var11 + (var9 - var4)) / var11) > var8) {
            var7 = var8;
        }

        var0 = var12;

        for(var1 = var11; var5 < var7; var4 += var1) {
            var13 = var5++;
            var3[var13] += ((var0 << 8) + (var2[var4 >> 8] - var0) * (var4 & 255)) * var6 >> 6;
        }

        var10.attackEnvelopeFactor = var4;
        return var5;
    }

    static int method2531(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, RawPcmStream var11, int var12, int var13) {
        if(var12 == 0 || (var8 = var5 + (var10 + 256 - var4 + var12) / var12) > var9) {
            var8 = var9;
        }

        var5 <<= 1;

        int var14;
        for(var8 <<= 1; var5 < var8; var4 += var12) {
            var1 = var4 >> 8;
            byte var15 = var2[var1 - 1];
            var0 = (var2[var1] - var15) * (var4 & 255) + (var15 << 8);
            var14 = var5++;
            var3[var14] += var0 * var6 >> 6;
            var14 = var5++;
            var3[var14] += var0 * var7 >> 6;
        }

        if(var12 == 0 || (var8 = (var5 >> 1) + (var10 - var4 + var12) / var12) > var9) {
            var8 = var9;
        }

        var8 <<= 1;

        for(var1 = var13; var5 < var8; var4 += var12) {
            var0 = (var1 << 8) + (var4 & 255) * (var2[var4 >> 8] - var1);
            var14 = var5++;
            var3[var14] += var0 * var6 >> 6;
            var14 = var5++;
            var3[var14] += var0 * var7 >> 6;
        }

        var11.attackEnvelopeFactor = var4;
        return var5 >> 1;
    }

    static int method2532(byte[] var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, RawPcmStream var9) {
        var2 >>= 8;
        var8 >>= 8;
        var4 <<= 2;
        var5 <<= 2;
        if((var6 = var3 + var8 - var2) > var7) {
            var6 = var7;
        }

        var9.rightChannelPan += var9.__a * (var6 - var3);
        var9.leftChannelPan += var9.__z * (var6 - var3);

        int var10;
        for(var6 -= 3; var3 < var6; var4 += var5) {
            var10 = var3++;
            var1[var10] += var0[var2++] * var4;
            var4 += var5;
            var10 = var3++;
            var1[var10] += var0[var2++] * var4;
            var4 += var5;
            var10 = var3++;
            var1[var10] += var0[var2++] * var4;
            var4 += var5;
            var10 = var3++;
            var1[var10] += var0[var2++] * var4;
        }

        for(var6 += 3; var3 < var6; var4 += var5) {
            var10 = var3++;
            var1[var10] += var0[var2++] * var4;
        }

        var9.__u = var4 >> 2;
        var9.attackEnvelopeFactor = var2 << 8;
        return var3;
    }

    static int method2533(int var0, byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, RawPcmStream var12) {
        var3 >>= 8;
        var11 >>= 8;
        var5 <<= 2;
        var6 <<= 2;
        var7 <<= 2;
        var8 <<= 2;
        if((var9 = var11 + var4 - var3) > var10) {
            var9 = var10;
        }

        var12.__u += var12.__i * (var9 - var4);
        var4 <<= 1;
        var9 <<= 1;

        byte var13;
        int var14;
        for(var9 -= 6; var4 < var9; var6 += var8) {
            var13 = var1[var3++];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
            var6 += var8;
            var13 = var1[var3++];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
            var6 += var8;
            var13 = var1[var3++];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
            var6 += var8;
            var13 = var1[var3++];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
        }

        for(var9 += 6; var4 < var9; var6 += var8) {
            var13 = var1[var3++];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
        }

        var12.rightChannelPan = var5 >> 2;
        var12.leftChannelPan = var6 >> 2;
        var12.attackEnvelopeFactor = var3 << 8;
        return var4 >> 1;
    }

    static int method2534(byte[] var0, int[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, RawPcmStream var9) {
        var2 >>= 8;
        var8 >>= 8;
        var4 <<= 2;
        var5 <<= 2;
        if((var6 = var3 + var2 - (var8 - 1)) > var7) {
            var6 = var7;
        }

        var9.rightChannelPan += var9.__a * (var6 - var3);
        var9.leftChannelPan += var9.__z * (var6 - var3);

        int var10;
        for(var6 -= 3; var3 < var6; var4 += var5) {
            var10 = var3++;
            var1[var10] += var0[var2--] * var4;
            var4 += var5;
            var10 = var3++;
            var1[var10] += var0[var2--] * var4;
            var4 += var5;
            var10 = var3++;
            var1[var10] += var0[var2--] * var4;
            var4 += var5;
            var10 = var3++;
            var1[var10] += var0[var2--] * var4;
        }

        for(var6 += 3; var3 < var6; var4 += var5) {
            var10 = var3++;
            var1[var10] += var0[var2--] * var4;
        }

        var9.__u = var4 >> 2;
        var9.attackEnvelopeFactor = var2 << 8;
        return var3;
    }

    static int method2547(int var0, byte[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, RawPcmStream var12) {
        var3 >>= 8;
        var11 >>= 8;
        var5 <<= 2;
        var6 <<= 2;
        var7 <<= 2;
        var8 <<= 2;
        if((var9 = var3 + var4 - (var11 - 1)) > var10) {
            var9 = var10;
        }

        var12.__u += var12.__i * (var9 - var4);
        var4 <<= 1;
        var9 <<= 1;

        byte var13;
        int var14;
        for(var9 -= 6; var4 < var9; var6 += var8) {
            var13 = var1[var3--];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
            var6 += var8;
            var13 = var1[var3--];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
            var6 += var8;
            var13 = var1[var3--];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
            var6 += var8;
            var13 = var1[var3--];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
        }

        for(var9 += 6; var4 < var9; var6 += var8) {
            var13 = var1[var3--];
            var14 = var4++;
            var2[var14] += var13 * var5;
            var5 += var7;
            var14 = var4++;
            var2[var14] += var13 * var6;
        }

        var12.rightChannelPan = var5 >> 2;
        var12.leftChannelPan = var6 >> 2;
        var12.attackEnvelopeFactor = var3 << 8;
        return var4 >> 1;
    }

    static int method2536(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, RawPcmStream var11, int var12, int var13) {
        var11.rightChannelPan -= var11.__a * var5;
        var11.leftChannelPan -= var11.__z * var5;
        if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12 - 257) / var12) > var9) {
            var8 = var9;
        }

        byte var14;
        int var15;
        while(var5 < var8) {
            var1 = var4 >> 8;
            var14 = var2[var1];
            var15 = var5++;
            var3[var15] += ((var14 << 8) + (var2[var1 + 1] - var14) * (var4 & 255)) * var6 >> 6;
            var6 += var7;
            var4 += var12;
        }

        if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12 - 1) / var12) > var9) {
            var8 = var9;
        }

        for(var1 = var13; var5 < var8; var4 += var12) {
            var14 = var2[var4 >> 8];
            var15 = var5++;
            var3[var15] += ((var14 << 8) + (var1 - var14) * (var4 & 255)) * var6 >> 6;
            var6 += var7;
        }

        var11.rightChannelPan += var11.__a * var5;
        var11.leftChannelPan += var11.__z * var5;
        var11.__u = var6;
        var11.attackEnvelopeFactor = var4;
        return var5;
    }

    static int method2504(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, RawPcmStream var13, int var14, int var15) {
        var13.__u -= var5 * var13.__i;
        if(var14 == 0 || (var10 = var5 + (var12 - var4 + var14 - 257) / var14) > var11) {
            var10 = var11;
        }

        var5 <<= 1;

        byte var16;
        int var17;
        for(var10 <<= 1; var5 < var10; var4 += var14) {
            var1 = var4 >> 8;
            var16 = var2[var1];
            var0 = (var16 << 8) + (var4 & 255) * (var2[var1 + 1] - var16);
            var17 = var5++;
            var3[var17] += var0 * var6 >> 6;
            var6 += var8;
            var17 = var5++;
            var3[var17] += var0 * var7 >> 6;
            var7 += var9;
        }

        if(var14 == 0 || (var10 = (var5 >> 1) + (var12 - var4 + var14 - 1) / var14) > var11) {
            var10 = var11;
        }

        var10 <<= 1;

        for(var1 = var15; var5 < var10; var4 += var14) {
            var16 = var2[var4 >> 8];
            var0 = (var16 << 8) + (var1 - var16) * (var4 & 255);
            var17 = var5++;
            var3[var17] += var0 * var6 >> 6;
            var6 += var8;
            var17 = var5++;
            var3[var17] += var0 * var7 >> 6;
            var7 += var9;
        }

        var5 >>= 1;
        var13.__u += var13.__i * var5;
        var13.rightChannelPan = var6;
        var13.leftChannelPan = var7;
        var13.attackEnvelopeFactor = var4;
        return var5;
    }

    static int method2538(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, RawPcmStream var11, int var12, int var13) {
        var11.rightChannelPan -= var11.__a * var5;
        var11.leftChannelPan -= var11.__z * var5;
        if(var12 == 0 || (var8 = var5 + (var10 + 256 - var4 + var12) / var12) > var9) {
            var8 = var9;
        }

        int var14;
        while(var5 < var8) {
            var1 = var4 >> 8;
            byte var15 = var2[var1 - 1];
            var14 = var5++;
            var3[var14] += ((var15 << 8) + (var2[var1] - var15) * (var4 & 255)) * var6 >> 6;
            var6 += var7;
            var4 += var12;
        }

        if(var12 == 0 || (var8 = var5 + (var10 - var4 + var12) / var12) > var9) {
            var8 = var9;
        }

        var0 = var13;

        for(var1 = var12; var5 < var8; var4 += var1) {
            var14 = var5++;
            var3[var14] += ((var0 << 8) + (var2[var4 >> 8] - var0) * (var4 & 255)) * var6 >> 6;
            var6 += var7;
        }

        var11.rightChannelPan += var11.__a * var5;
        var11.leftChannelPan += var11.__z * var5;
        var11.__u = var6;
        var11.attackEnvelopeFactor = var4;
        return var5;
    }

    static int method2624(int var0, int var1, byte[] var2, int[] var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, RawPcmStream var13, int var14, int var15) {
        var13.__u -= var5 * var13.__i;
        if(var14 == 0 || (var10 = var5 + (var12 + 256 - var4 + var14) / var14) > var11) {
            var10 = var11;
        }

        var5 <<= 1;

        int var16;
        for(var10 <<= 1; var5 < var10; var4 += var14) {
            var1 = var4 >> 8;
            byte var17 = var2[var1 - 1];
            var0 = (var2[var1] - var17) * (var4 & 255) + (var17 << 8);
            var16 = var5++;
            var3[var16] += var0 * var6 >> 6;
            var6 += var8;
            var16 = var5++;
            var3[var16] += var0 * var7 >> 6;
            var7 += var9;
        }

        if(var14 == 0 || (var10 = (var5 >> 1) + (var12 - var4 + var14) / var14) > var11) {
            var10 = var11;
        }

        var10 <<= 1;

        for(var1 = var15; var5 < var10; var4 += var14) {
            var0 = (var1 << 8) + (var4 & 255) * (var2[var4 >> 8] - var1);
            var16 = var5++;
            var3[var16] += var0 * var6 >> 6;
            var6 += var8;
            var16 = var5++;
            var3[var16] += var0 * var7 >> 6;
            var7 += var9;
        }

        var5 >>= 1;
        var13.__u += var13.__i * var5;
        var13.rightChannelPan = var6;
        var13.leftChannelPan = var7;
        var13.attackEnvelopeFactor = var4;
        return var5;
    }
}
