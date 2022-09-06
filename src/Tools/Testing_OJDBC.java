package Tools;

import Daos.RegionDAO;
import IDaos.IRegionDAO;

public class Testing_OJDBC {
    public static void main(String[] args) throws Exception {
          DBConnection connection = new DBConnection();
//  ===============================================================================================
//        Region
        IRegionDAO irdao = new RegionDAO(connection.getConnection());
//        IRegionController controller = new RegionController(connection.getConnection());

//  ===============================================================================================
//        Job
//          IJobDAO iJobDAO = new JobDAO(connection.getConnection());
//          IJobController ijc = new JobController(connection.getConnection());
//  ================================================================================================
//        IJobDAO

//        Get All Job
//        iJobDAO.getAll().forEach(j -> {
//            System.out.println("Id : " + j.getId());
//            System.out.println("Title : " + j.getTitle());
//            System.out.println("Max : " + j.getMaxSalary());
//            System.out.println("Min : " + j.getMinSalary());
//        });

//        Get By Id Job
//        Job j = iJobDAO.getById("ad_pres");
//        System.out.println("Id : " + j.getId());
//            System.out.println("Title : " + j.getTitle());
//            System.out.println("Max : " + j.getMaxSalary());
//            System.out.println("Min : " + j.getMinSalary());

//        Search Key Job
//        iJobDAO.search("ce").forEach(s -> {
//            System.out.println("Id : " + s.getId());
//            System.out.println("Title : " + s.getTitle());
//        System.out.println("Min : " + s.getMinSalary());
//        System.out.println("Max : " + s.getMaxSalary());
//        });

//        Insert Job
//        Job job = new Job("it_secr","Cyber Security",4000,10000);
//        System.out.println(iJobsDAO.insert(job));

//        Update Job
//        Job job = new Job("vkjshndf","Office Boy",1000,5000);
//        System.out.println(iJobsDAO.update(job));

//        Delete Job
//        System.out.println(iJobsDAO.delete("it_secr"));

//  ====================================================================================================
//          IJob Controller

//          Get All
//            ijc.getAll().forEach(j -> {
//            System.out.println("Id : " + j.getId());
//            System.out.println("Title : " + j.getTitle());
//            System.out.println("Max : " + j.getMaxSalary());
//            System.out.println("Min : " + j.getMinSalary());
//            });

//            Get By Id
//  ====================================================================================================
//        Irdao

//        Get All Region
//        irdao.getAll().forEach(p -> System.out.println(p.getId() + "|" + p.getName()));

//        for (Region region : irdao.getAll()) {
//            System.out.println("Id   : " + region.getId());
//            System.out.println("Name : " + region.getName());
//        }

//        Get By Id Region
//        Region r = irdao.getById(4);
//        System.out.println("Id : " + r.getId());
//        System.out.println("Name : " + r.getName());

//        Get By Name
//        Region r = irdao.getByName("Asia");
//        System.out.println(r.getName());
//        System.out.println(r.getId());


//        Search
//        irdao.search("Asia").forEach(p -> System.out.println(p.getId() + " | " + p.getName()));
//        for (Region r : irdao.search("si")) {
//            System.out.println("Id   : " + r.getId());
//            System.out.println("Name : " + r.getName());
//        }

//        Insert
//        Region regionInsert = new Region(31,"Tajmahal");
//        System.out.println(irdao.insert(regionInsert));

//        Update
//        Region region = new Region(31,"Brazil");
//        System.out.println(irdao.update(region));

//        Delete
//        System.out.println(irdao.delete(31));

//        Get region_id
//        System.out.println(irdao.hasFK(7));

//        ==============================================================================
//        Controller

//        Get All Controller
//        for (Region r: controller2.getAll()) {
//            System.out.println(r.getId());
//            System.out.println(r.getName());
//        }

//        Get By Id Controller
//            Region r = controller2.getById("40");
//            System.out.println("Id : " + r.getId());
//            System.out.println("Name : " + r.getName());

//        Insert Controller
//        System.out.println(controller2.insert("23","Barcelona"));

//        Delete Controller
//        System.out.println(controller2.delete("23"));

//        Search by Controller
//            Cara 1
//            for (Region region : controller2.search("puluhanribu")) {
//            System.out.println("Id   : " + r.getId());
//            System.out.println("Name : " + r.getName());
//        Cara 2
//        List<Region> search = controller2.search("si");
//        search.forEach(s -> {
//            System.out.println("Id   : " + s.getId());
//            System.out.println("Name : " + s.getName());
//        });
//

//        Update By Controller
//        System.out.println(controller2.update("1","Europe"));
    }
}
