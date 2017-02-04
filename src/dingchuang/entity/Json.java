/**
 * 与前端之间的接口，用来表示是否成功
 */
package dingchuang.entity;

public class Json {
	private String msg;
	private boolean success;

	public Json() {
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setSuccess(boolean b) {
		this.success = b;
	}

	public boolean getSuccess() {
		if (success != true)
			return false;
		else
			return true;
	}
}
