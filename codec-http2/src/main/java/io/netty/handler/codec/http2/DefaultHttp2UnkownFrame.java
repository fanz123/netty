/*
 * Copyright 2017 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.buffer.Unpooled;

public final class DefaultHttp2UnkownFrame extends DefaultByteBufHolder implements Http2UnknownFrame {
    private final byte frameType;
    private final Http2Flags flags;
    private Http2FrameStream stream;

    public DefaultHttp2UnkownFrame(byte frameType, Http2Flags flags) {
        this(frameType, flags, Unpooled.EMPTY_BUFFER);
    }

    public DefaultHttp2UnkownFrame(byte frameType, Http2Flags flags, ByteBuf data) {
        super(data);
        this.frameType = frameType;
        this.flags = flags;
    }

    @Override
    public Http2FrameStream stream() {
        return stream;
    }

    @Override
    public DefaultHttp2UnkownFrame stream(Http2FrameStream stream) {
        this.stream = stream;
        return this;
    }

    @Override
    public byte frameType() {
        return frameType;
    }

    @Override
    public Http2Flags flags() {
        return flags;
    }

    @Override
    public String name() {
        return "UNKNOWN";
    }

    @Override
    public DefaultHttp2UnkownFrame copy() {
        return replace(content().copy());
    }

    @Override
    public DefaultHttp2UnkownFrame duplicate() {
        return replace(content().duplicate());
    }

    @Override
    public DefaultHttp2UnkownFrame retainedDuplicate() {
        return replace(content().retainedDuplicate());
    }

    @Override
    public DefaultHttp2UnkownFrame replace(ByteBuf content) {
        return new DefaultHttp2UnkownFrame(frameType, flags, content).stream(stream());
    }

    @Override
    public DefaultHttp2UnkownFrame retain() {
        super.retain();
        return this;
    }

    @Override
    public DefaultHttp2UnkownFrame retain(int increment) {
        super.retain(increment);
        return this;
    }

    @Override
    public String toString() {
        return "DefaultHttp2UnkownFrame(frameType=" + frameType() + ", stream=" + stream() + ", flags=" + flags() +
                ", content=" + contentToString() + ")";
    }

    @Override
    public DefaultHttp2UnkownFrame touch() {
        super.touch();
        return this;
    }

    @Override
    public DefaultHttp2UnkownFrame touch(Object hint) {
        super.touch(hint);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DefaultHttp2UnkownFrame)) {
            return false;
        }
        DefaultHttp2UnkownFrame other = (DefaultHttp2UnkownFrame) o;
        return super.equals(other) && flags().equals(other.flags())
                && frameType() == other.frameType() && (stream() == null && other.stream() == null) ||
                stream().equals(other.stream());
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = hash * 31 + frameType();
        hash = hash * 31 + flags().hashCode();
        if (stream() != null) {
            hash = hash * 31 + stream().hashCode();
        }

        return hash;
    }
}
