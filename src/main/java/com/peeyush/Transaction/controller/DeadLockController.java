package com.peeyush.Transaction.controller;

import com.peeyush.Transaction.service.DeadLockService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deadlock")
public class DeadLockController {
    private final DeadLockService deadlockService;

    public DeadLockController(DeadLockService deadLockService){
        this.deadlockService = deadLockService;
    }

    @GetMapping("/lockAThenB/{aId}/{bId}")
    public String lockAThenB(@PathVariable Long aId,@PathVariable Long bId){
        deadlockService.lockAThenB(aId, bId);
        return "DeadLock Experiment Started";
    }

    @GetMapping("/lockBThenA/{aId}/{bId}")
    public String lockBThenA(@PathVariable Long aId,@PathVariable Long bId){
        deadlockService.lockBThenA(aId, bId);
        return "DeadLock Experiment Completed";
    }
}
