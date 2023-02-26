package su.dedvano.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su.dedvano.domain.Folder;
import su.dedvano.repository.FolderRepository;

@Service
@Transactional
public class FolderService {

    private final FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    public Folder create() {
        return null;
    }
}
