package model;

public class JoinDTO {

	private String id;
	private int rating;
	private String mem_name;
	private String nickname;
	private int mem_no;
	private int group_no;
	private int mgn_no;
	private int board_category;
	private int comment_no;
	private String comment_writer;
	private int board_no;
	private String board_title;
	private String board_writer;
	private String board_date;
	private int board_hit;
	private int board_like;
	private String city;
	private String town;
	private int area_no;
	private String l_category;
	private String s_category;
	private int interest_no;
	private String group_name;
	private String group_main_img;
	private int group_interest;
	private int group_area;
	private String pwd;
	private String profile_img;
	private String birth;
	private String e_mail;
	private String phone;
	private String check_q;
	private String check_a;
	private String area1;
	private String area2;
	private String area3;
	private String interests;
	
	public int getMgn_no() {
		return mgn_no;
	}

	public void setMgn_no(int mgn_no) {
		this.mgn_no = mgn_no;
	}

	public int getBoard_category() {
		return board_category;
	}

	public void setBoard_category(int board_category) {
		this.board_category = board_category;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}

	public int getBoard_hit() {
		return board_hit;
	}

	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}

	public int getBoard_like() {
		return board_like;
	}

	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public int getArea_no() {
		return area_no;
	}

	public void setArea_no(int area_no) {
		this.area_no = area_no;
	}

	public String getL_category() {
		return l_category;
	}

	public void setL_category(String l_category) {
		this.l_category = l_category;
	}

	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
	}

	public int getInterest_no() {
		return interest_no;
	}

	public void setInterest_no(int interest_no) {
		this.interest_no = interest_no;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_main_img() {
		return group_main_img;
	}

	public void setGroup_main_img(String group_main_img) {
		this.group_main_img = group_main_img;
	}

	public int getGroup_interest() {
		return group_interest;
	}

	public void setGroup_interest(int group_interest) {
		this.group_interest = group_interest;
	}

	public int getGroup_area() {
		return group_area;
	}

	public void setGroup_area(int group_area) {
		this.group_area = group_area;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCheck_q() {
		return check_q;
	}

	public void setCheck_q(String check_q) {
		this.check_q = check_q;
	}

	public String getCheck_a() {
		return check_a;
	}

	public void setCheck_a(String check_a) {
		this.check_a = check_a;
	}

	public String getArea1() {
		return area1;
	}

	public void setArea1(String area1) {
		this.area1 = area1;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getArea3() {
		return area3;
	}

	public void setArea3(String area3) {
		this.area3 = area3;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public int getGroup_no() {
		return group_no;
	}

	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}

	@Override
	public String toString() {

		return "[" + "id:" + id + "," + "rating:" + rating + "," + "mem_name:" + mem_name + ", nickname:" + nickname + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getMem_no() {
		return mem_no;
	}

	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
}
